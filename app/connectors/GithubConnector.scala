package connectors

import cats.data.EitherT
import models.{APIError, GitUser, Repository, RepositoryList, User}
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
        Right(User((gitUser \ "login").as[String], (gitUser \ "created_at").as[String], (gitUser \ "location").asOpt[String], (gitUser \ "followers").as[Int], (gitUser \ "following").as[Int]))
    }
      .recover {
        case _ =>
      Left(APIError.BadAPIResponse(400, "Could not return user"))
    }
  }

  def getRepoList[Response](url: String)(implicit rds: OFormat[Response], ec: ExecutionContext): EitherT[Future, APIError, List[Repository]] = {
    val request = ws.url(url)
    val response = request.get()
    EitherT {
      response
        .map {
          result => {
            result.json.validate[RepositoryList] match {
              case JsSuccess(value, _) =>
                Right(value.repoList)
              case JsError(errors) =>
                Left(APIError.BadAPIResponse(400, "Could not find book"))
            }}
        }
    }
  }

//  def getAllUsers = {}
}