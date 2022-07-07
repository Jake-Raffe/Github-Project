package connectors

import models.{APIError, Content, GitUser, Repository, RepositoryList, User}
import play.api.libs.json.{JsError, JsSuccess, OFormat}
import play.api.libs.ws.{WSClient, WSResponse}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class GithubConnector @Inject()(ws: WSClient) {
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
      Left(APIError.BadAPIResponse(400, "Could not return user"))
    }
  }

  def getRepoList[Response](url: String)(implicit rds: OFormat[Response], ec: ExecutionContext):  Future[Either[APIError, List[Repository]]] = {
    val request = ws.url(url).get()
    request.map {
      result =>
        val repoList = result.json
        repoList.validate[List[Repository]] match {
          case JsSuccess(repos, _) => Right(repos.map(repo => Repository(repo.name)))
          case JsError(errors) => Left(APIError.BadAPIResponse(400, "Could not validate repo"))
        }
    }
  }

  def getRepoContent[Response](url: String)(implicit rds: OFormat[Response], ec: ExecutionContext):  Future[Either[APIError, List[Content]]] = {
    val request = ws.url(url).get()
    request.map {
      result =>
        val repoContents = result.json
        repoContents.validate[List[Content]] match {
          case JsSuccess(contents, _) => Right(contents.map(content => Content(content.name, content.contentType, content.size)))
          case JsError(errors) => Left(APIError.BadAPIResponse(400, "Could not validate repo"))
        }
    }
  }

//  def getAllUsers = {}
}