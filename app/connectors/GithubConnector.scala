package connectors

import models.{APIError, Content, FileContent, GitUser, Repository, User}
import play.api.libs.json.{JsError, JsLookupResult, JsSuccess, JsValue, OFormat}
import play.api.libs.ws.{WSClient, WSResponse}
import play.mvc.BodyParser.Raw

import java.util.Base64
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

class GithubConnector @Inject()(ws: WSClient) {
  import GithubConnector._

  def getUser[Response](url: String)(implicit rds: OFormat[Response], ec: ExecutionContext): Future[Either[APIError, User]] = {
    val request = ws.url(url).get()
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

  def getRepoList[Response](url: String)(implicit rds: OFormat[Response], ec: ExecutionContext):  Future[Either[APIError, List[Repository]]] = {
    val request = ws.url(url).get()
    request.map {
      result =>
        val repoList = result.json
        repoList.validate[List[Repository]] match {
          case JsSuccess(repos, _) => Right(repos.map(repo => Repository(repo.name)))
          case JsError(errors) => Left(APIError.BadAPIResponse(400, "Unable to access/validate repositories"))
        }
    }
  }

  def getRepoContent[Response](url: String)(implicit rds: OFormat[Response], ec: ExecutionContext):  Future[Either[APIError, List[Content]]] = {
    val request = ws.url(url).get()
    request.map {
      result =>
        val repoContents = result.json
        repoContents.validate[List[Content]] match {
          case JsSuccess(contents, _) => Right(contents.map(content => Content(content.name, content.`type`)))
          case JsError(errors) => Left(APIError.BadAPIResponse(400, "Unable to validate content in repository"))
        }
    }
  }

  def getRepoContentDeeper[Response](url: String)(implicit rds: OFormat[Response], ec: ExecutionContext):  Future[Either[APIError, List[Content]]] = {
    val request = ws.url(url).get()
    request.map {
      result =>
        val repoContents = result.json
        repoContents.validate[List[Content]] match {
          case JsSuccess(contents, _) => Right(contents.map(content => Content(content.name, content.`type`)))
          case JsError(errors) => Left(APIError.BadAPIResponse(400, "Unable to validate content in repository, this is the deeper level one"))
        }
    }
  }

  def getFileContents[Response](url: String)(implicit rds: OFormat[Response], ec: ExecutionContext):  Future[Either[APIError, String]] = {
    val request = ws.url(url).get()
    request.map {
      result => Right(parseFileContents(result.json))
    }
      .recover {
        case _ =>
          Left(APIError.BadAPIResponse(400, "Unable to return file contents at connector"))
      }
  }
}


object GithubConnector {
  def parseFileContents(response: JsValue): String = {
    val output = (response \ "content").as[String]
    output
  }
}
