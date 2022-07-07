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
    githubService.getUser(username).map {
      case Right(user) => Ok(views.html.userPage(user))
      case Left(_) => Ok(views.html.notFound(username))
    }
  }

  def getUserRepositories(username: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    githubService.getUserRepo(username).map {
      case Right(repo) => Ok(views.html.userReposPage(username)(repo))
      case Left(_) => Ok(views.html.notFound(s"$username's repo"))
    }
  }

  def getUserRepositoryContents(username: String, repoName: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    githubService.getRepoContents(username, repoName).map {
      case Right(contents) => Ok(views.html.userRepoContentsPage(username)(repoName)(contents))
      case Left(_) => Ok(views.html.notFound(s"$username/$repoName contents"))
    }
  }
}
