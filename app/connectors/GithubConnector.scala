package connectors

import akka.http.scaladsl.model.{HttpHeader, HttpMethods, HttpRequest, Uri}
import models.{APIError, Content, CreatedFile, DeleteFile, ExistingFile, FileContent, FileForm, Repository, UpdatedFile, User}
import org.joda.time.Seconds.seconds
import play.api.libs.json.{JsError, JsLookupResult, JsSuccess, JsValue, Json, OFormat}
import play.api.libs.ws.{WSAuthScheme, WSClient, WSResponse}
import play.api.mvc.Headers
import play.mvc.BodyParser.Raw

import java.time.Duration
import java.time.temporal.TemporalUnit
import sys.process._
import java.util.Base64
import javax.inject.Inject
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.sys.env
import scala.util.{Left, Try}

class GithubConnector @Inject()(ws: WSClient) {

  import GithubConnector._

  def getUser[Response](username: String)(implicit rds: OFormat[Response], ec: ExecutionContext): Future[Either[APIError, User]] = {
    val request = ws.url(s"https://api.github.com/users/${username}").get()
    request.map {
      result =>
        val gitUser = result.json
        Right(User(
          (gitUser \ "login").as[String],
          (gitUser \ "created_at").as[String],
          (gitUser \ "location").asOpt[String],
          (gitUser \ "followers").as[Int],
          (gitUser \ "following").as[Int]
        ))
    }
      .recover {
        case _ =>
          Left(APIError.BadAPIResponse(404, "User not found"))
      }
  }

  def getRepoList[Response](username: String)(implicit rds: OFormat[Response], ec: ExecutionContext): Future[Either[APIError, List[Repository]]] = {
    val request = ws.url(s"https://api.github.com/users/${username}/repos").get()
    request.map {
      result =>
        val repoList = result.json
        repoList.validate[List[Repository]] match {
          case JsSuccess(repos, _) => Right(repos.map(repo => Repository(repo.name)))
          case JsError(errors) => Left(APIError.BadAPIResponse(400, "Unable to access/validate repositories"))
        }
    }
  }

  def getRepoContent[Response](username: String, repoName: String, path: String)(implicit rds: OFormat[Response], ec: ExecutionContext): Future[Either[APIError, List[Content]]] = {
    val request = ws.url(s"https://api.github.com/repos/${username}/${repoName}/contents$path").get()
    request.map {
      result =>
        val repoContents = result.json
        repoContents.validate[List[Content]] match {
          case JsSuccess(contents, _) => Right(contents.map(content => Content(content.name, content.`type`, content.path, content.sha)))
          case JsError(errors) => Left(APIError.BadAPIResponse(400, "Unable to validate content in repository"))
        }
    }
  }

  def getFileContents[Response](username: String, repoName: String, path: String)(implicit rds: OFormat[Response], ec: ExecutionContext): Future[Either[APIError, String]] = {
    val request = ws.url(s"https://api.github.com/repos/${username}/${repoName}/contents/$path").get()
    request.map {
      result => Right(parseFileContents(result.json))
    }
      .recover {
        case _ =>
          Left(APIError.BadAPIResponse(400, "Unable to return file contents at connector"))
      }
  }
  def getFileNameContentsSha[Response](username: String, repoName: String, path: String)(implicit rds: OFormat[Response], ec: ExecutionContext): Future[Either[APIError, ExistingFile]] = {
    val request = ws.url(s"https://api.github.com/repos/${username}/${repoName}/contents/$path").get()
    request.map {
      result => Right(parseFileNameContentsSha(result.json))
      }
      .recover {
        case _ =>
          Left(APIError.BadAPIResponse(400, "Unable to return file sha at connector"))
      }
  }

//  def createNewFile[Response](username: String, repoName: String, path: String, fileName: String, encodedContent: String)(implicit
//                                                                                                                          rds: OFormat[Response],
//                                                                                                                          ec: ExecutionContext): Future[Either[APIError, String]] = {
//    val url = s"https://api.github.com/repos/${username}/${repoName}/contents/$path/$fileName"
//    println(url + path)
//    val authentication = env.getOrElse("AUTHTOKEN", "empty")
//    val createdFile = CreatedFile(s"Created new file: $fileName", encodedContent, "main")
//    val jsonFile = Json.toJson(createdFile)
//    val request = ws.url(url)
//      .withHttpHeaders("Authorization" -> s"token $authentication")
//      .withHttpHeaders("Accept" -> "application/vnd.github+json")
//    println(s"----- url: ${request}")
//    request.put(jsonFile).map {
//      case response: WSResponse if (response.status.equals(200)) => Right({println("--------- this is the response: " +response); "success"})
//      case response: WSResponse if (response.status.equals(404)) =>  Left({println("--------- this is the response: " +response); APIError.BadAPIResponse(response.status, s"Unable to update file: ${response.json}")})
//      case exception: Exception => Left(APIError.BadAPIResponse(400, "Unable to create new file"))
//      case _ => Left(APIError.BadAPIResponse(400, "Other output"))
//    }
//  }

//  def updateFile[Response](username: String, repoName: String, path: String, fileName: String, encodedContent: String, sha: String)(implicit rds: OFormat[Response], ec: ExecutionContext): Future[Either[APIError, String]] = {
//    val url = s"https://api.github.com/repos/${username}/${repoName}/contents/$path?ref=main"
//    println(url)
//    val authentication = env.getOrElse("AUTHTOKEN", "empty")
////    val updatedFile = UpdatedFile(s"Updated file: $fileName", encodedContent, sha, "main")
//    val updatedFile = UpdatedFile(s"Updated file: $fileName", encodedContent, sha, Committer("Jake-Raffe", "jakeraffe24@gmail.com"))
//    val jsonFile = Json.toJsObject(updatedFile)
//    println("----------" + jsonFile)
//    val request = ws.url(url)
//      .withHttpHeaders("Authorization" -> s"token $authentication")
//      .withHttpHeaders("Accept" -> "application/vnd.github+json")
//      .put(jsonFile)
//    request.map {
//      case response: WSResponse if (response.status.equals(200)) =>  Right({println("--------- this is the response: " +response); "success"})
//      case response: WSResponse if (response.status.equals(404)) =>  Left({println("--------- this is the response: " +response); APIError.BadAPIResponse(response.status, s"Unable to update file: ${response.json}")})
//      case exception: Exception => Left(APIError.BadAPIResponse(400, "Unable to update file"))
//      case _ => Left(APIError.BadAPIResponse(400, "Other output"))
//    }
//  }

  def createNewFileCurl[Response](username: String, repoName: String, path: String, fileName: String, encodedContent: String)(implicit rds: OFormat[Response], ec: ExecutionContext): Future[Either[APIError, String]] = {
    val editedPath = if (path.equals("")) "" else s"$path/"
    val request = GithubConnector.createCurlRequest(username, repoName, editedPath, fileName, encodedContent)
    request.!!
    getFileContents(username, repoName, s"$editedPath$fileName").map {
      case Right(value) => Right("success")
      case Left(value) => Left(value)
    }
  }

  def updateFileCurl[Response](username: String, repoName: String, path: String, fileName: String, encodedContent: String, sha: String)(implicit rds: OFormat[Response], ec: ExecutionContext): Future[Either[APIError, String]] = {
    val request = GithubConnector.updateCurlRequest(username, repoName, path, fileName, encodedContent, sha)
    request.!!
    getFileContents(username, repoName, s"$path").map {
//      case Right(contents) if contents.equals(encodedContent) => Right("success")
      case Right(contents) => Right("success")
//      case Right(contents) => Left(APIError.BadAPIResponse(400, "Didn't update contents of file"))
      case Left(value) => Left(value)
    }
  }

  def deleteFileCurl[Response](username: String, repoName: String, path: String, fileName: String, sha: String)(implicit rds: OFormat[Response], ec: ExecutionContext): Future[Either[APIError, String]] = {
    val request = GithubConnector.deleteCurlRequest(username, repoName, path, fileName, sha)
//    println(request.!! + "--------------\n" + s"$path$fileName")
    println(request.!!)
    Thread.sleep(100)
    getFileContents(username, repoName, s"$path$fileName").map {
      case Right(contents) => Left(APIError.BadAPIResponse(400, "File contents still found i.e. wasn't deleted"))
      case Left(value) => Right({println(value);"success"})
    }
  }

}


object GithubConnector {
  def parseFileContents(response: JsValue): String = {
    val output = (response \ "content").as[String]
    output
  }
  def parseFileNameContentsSha(response: JsValue): ExistingFile = {
    val fileName = (response \ "name").as[String]
    val contents = (response \ "content").as[String]
    val sha = (response \ "sha").as[String]
    ExistingFile(fileName, contents, sha)
  }

  def createCurlRequest(username: String, repoName: String, path: String, fileName: String, encodedContent: String): List[String] = {
    val authentication = env.getOrElse("AUTH_TOKEN", "empty")
    val jsonBody = Json.toJson(CreatedFile(s"Created new file: $fileName", s"$encodedContent", "main"))
    val curlRequest = List("curl", "-XPUT", s"https://api.github.com/repos/$username/$repoName/contents/$path$fileName",
      "-H", "Accept: application/vnd.github+json", "-H", s"Authorization: token $authentication",
      "-d", s"$jsonBody")
    curlRequest
  }

  def updateCurlRequest(username: String, repoName: String, path: String, fileName: String, encodedContent: String, sha: String): List[String] = {
    val authentication = env.getOrElse("AUTH_TOKEN", "empty")
    val jsonBody = Json.toJson(UpdatedFile(s"Updated file: $fileName", s"$encodedContent", sha, "main"))
    val curlRequest = List("curl", "-XPUT", s"https://api.github.com/repos/$username/$repoName/contents/$path",
      "-H", "Accept: application/vnd.github+json", "-H", s"Authorization: token $authentication",
      "-d", s"$jsonBody")
    println(curlRequest)
    curlRequest
  }

  def deleteCurlRequest(username: String, repoName: String, path: String, fileName: String, sha: String): List[String] = {
    val authentication = env.getOrElse("AUTH_TOKEN", "empty")
    val jsonBody = Json.toJson(DeleteFile(s"Deleted file: $fileName", sha, "main"))
    val curlRequest = List("curl", "-XDELETE", s"https://api.github.com/repos/$username/$repoName/contents/$path$fileName",
      "-H", "Accept: application/vnd.github+json", "-H", s"Authorization: token $authentication",
      "-d", s"$jsonBody")
    curlRequest
  }
}
