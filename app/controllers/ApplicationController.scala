package controllers

import cats.data.EitherT
import models.APIError.BadAPIResponse
import models.{APIError, UpdateField, User}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.OFormat.oFormatFromReadsAndOWrites
import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import play.api.mvc._
import repositories.DataRepository
import services.{ApplicationService, GithubService}

import java.awt.print.Book
import java.security.Provider.Service
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class ApplicationController @Inject()(val controllerComponents: ControllerComponents,
                                      val githubService: GithubService,
                                      val applicationService: ApplicationService,
                                      implicit val ec: ExecutionContext
                                     ) extends BaseController {

  def index(): Action[AnyContent] = Action.async { implicit request =>
    applicationService.index().map{
      case Right(users: Seq[User]) => Ok(Json.toJson(users))
      case Left(error) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
    }
  }

  def create(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    applicationService.create(request).map {
      case Right(value) => Created(Json.toJson(value))
      case Left(error) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
    }
  }

  def read(username: String): Action[AnyContent] = Action.async { implicit request =>
    applicationService.read(username).map {
      case Right(value) => value
      case Left(error) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
    }
  }

  def update(username: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[User] match {
      case JsSuccess(data, _) =>
        applicationService.update(username, data).map {
          case Left(error) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
          case Right(value) => value
        }
      case JsError(_) => Future(BadRequest)
    }
  }

  def delete(username: String): Action[AnyContent] = Action.async { implicit request =>
    applicationService.delete(username).map {
      case Right(right) => right
      case Left(error) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
    }
  }

  def getUser(username: String): Action[AnyContent] = Action.async { implicit request =>
    githubService.getUser(username).map {
      case Right(user) => Ok(Json.toJson(user))
      case Left(error) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
    }
  }

  def getUserRepo(username: String): Action[AnyContent] = Action.async { implicit request =>
    githubService.getUserRepo(username = username).value.map {
      case Right(repoList) => Ok(Json.toJson(repoList))
      case Left(error) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
    }
  }
}
