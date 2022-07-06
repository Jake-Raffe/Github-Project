package services

import cats.data.EitherT
import models.APIError.BadAPIResponse

import javax.inject.Inject
import javax.inject.Singleton
import models.{APIError, User}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.OFormat.oFormatFromReadsAndOWrites
import play.api.libs.json._
import play.api.mvc.Results.{Accepted, BadRequest, Created, Ok}
import repositories.{DataRepository, DataRepositoryTrait}
import play.api.mvc._

import java.awt.print.Book
import java.security.Provider.Service
import javax.inject._
import scala.concurrent._

@Singleton
class ApplicationService @Inject()(dataRepository: DataRepositoryTrait)(implicit ec: ExecutionContext) {

  def index(): Future[Either[APIError, Seq[JsValue]]] = {
    dataRepository.index()
  }

  def create(request: Request[JsValue]): Future[Either[APIError, User]] =
    request.body.validate[User] match {
      case JsSuccess(user, _) => dataRepository.create(user)
      case JsError(_) => Future(Left(APIError.BadAPIResponse(415, "Unable to validate request body format")))
    }

  def read(username: String): Future[Either[APIError, Result]] = {
    dataRepository.read(username).map {
      case user if user.login.equals("empty") => Left(BadAPIResponse(404, s"Unable to find user of username: $username"))
      case user => Right(Ok(Json.toJson(user)))
      case _ => Left(BadAPIResponse(400, "Unable to complete request"))
    }
  }

  def update(username: String, newUser: User): Future[Either[APIError, Result]] =
    dataRepository.update(username, newUser).map {
      case Right(result) => Right(Accepted(Json.toJson(result)))
      case Left(error) =>Left(error)
    }

  def delete(username: String): Future[Either[APIError, Result]] =
    dataRepository.delete(username).map {
      case 1 => Right(Accepted)
      case _ => Left(BadAPIResponse(400, s"Unable to delete user of username: $username"))
    }

}
