package controllers

import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, Request}
import services.GithubService

import javax.inject._
import scala.concurrent.Future



@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents, applicationController: ApplicationController, githubService: GithubService) extends BaseController {


  def index(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(views.html.index()))
  }

//  def getUser(username: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
//    val userDetails = applicationController.getUser(username)
//    Future.successful(Ok(views.html.user(username)(userDetails)))
//  }

//  def getUserRepositories(username: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
//    Future.successful(Ok(views.html.userRepos(username)))
//  }
}
