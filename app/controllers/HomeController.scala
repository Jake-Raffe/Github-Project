package controllers

import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, Request}
import services.GithubService

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}



@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents, githubService: GithubService,
                               implicit val ec: ExecutionContext) extends BaseController {


  def index(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(views.html.index()))
  }

  def getUser(username: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
//    Future.successful(Ok(views.html.index()))
    githubService.getUser(username).map {
      case Right(user) => Ok(views.html.userPage(user))
    }
  }

  def getUserRepositories(username: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    githubService.getUserRepo(username).map {
      case Right(repo) => Ok(views.html.userReposPage(username)(repo))
    }
//    Future.successful(Ok(views.html.index()))
//    Future.successful(Ok(views.html.userRepos(username)))
  }
}
