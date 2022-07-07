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
      case Left(error) => Ok(views.html.notFound(username)(s"${error.httpResponseStatus}: ${error.reason}"))
    }
  }

  def getUserRepositories(username: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    githubService.getUserRepo(username).map {
      case Right(repo) => Ok(views.html.userReposPage(username)(repo))
      case Left(error) => Ok(views.html.notFound(s"$username's repo")(s"${error.httpResponseStatus}: ${error.reason}"))
    }
  }

  def getUserRepositoryContents(username: String, repoName: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    githubService.getRepoContents(username, repoName).map {
      case Right(contents) => Ok(views.html.userRepoContentsPage(username)(repoName)(contents))
      case Left(error) => Ok(views.html.notFound(s"$username/$repoName contents")(s"${error.httpResponseStatus}: ${error.reason}"))
    }
  }

  def getUserRepositoryContentsPath(username: String, repoName: String, path: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    githubService.getRepoContentsPath(username, repoName, path).map {
      case Right(contents) => Ok(views.html.userRepoContentsPathPage(username)(repoName)(path)(contents))
      case Left(error) => Ok(views.html.notFound(s"$username/$repoName contents")(s"${error.httpResponseStatus}: ${error.reason}"))
    }
  }
}
