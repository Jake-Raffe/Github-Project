package controllers

import models.{APIError, CreatedFile, FileForm}
import play.api.libs.json.JsValue
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, Request}
import services.GithubService
import forms.FormController

import java.net.URLEncoder
import javax.inject._
import scala.concurrent.{ExecutionContext, Future}
import scala.sys.env



@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents, githubService: GithubService,
                               implicit val ec: ExecutionContext) extends BaseController with play.api.i18n.I18nSupport {

  val fileFormTemplate = FileForm.fileForm

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

  def getUserRepositoryContents(username: String, repoName: String, path: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    githubService.getRepoContents(username, repoName, path).map {
      case Right(contents) => Ok(views.html.userRepoContentsPage(username,repoName,path)(contents))
      case Left(error) => Ok(views.html.notFound(s"$username/$repoName contents")(s"${error.httpResponseStatus}: ${error.reason}"))
    }
  }

//  def getUserRepositoryContentsPath(username: String, repoName: String, path: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
//    githubService.getRepoContentsPath(username, repoName, path).map {
//      case Right(contents) => Ok(views.html.userRepoContentsPathPage(username)(repoName)(path)(contents))
//      case Left(error) => Ok(views.html.notFound(s"$username/$repoName/$path contents")(s"${error.httpResponseStatus}: ${error.reason}"))
//    }
//  }

  def getFileContents(username: String, repoName: String, path: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    githubService.getFileContents(username, repoName, path).map {
      case Right(fileContent) => Ok(views.html.fileContentPage(username, repoName, path)(fileContent))
      case Left(error) => Ok(views.html.notFound(s"$username/$repoName contents")(s"${error.httpResponseStatus}: ${error.reason}"))
    }
  }

  def openNewFilePage(username: String, repoName: String, path: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(views.html.createNewFilePage(username, repoName, path, "")("create")(FileForm.fileForm)))
  }

  def openUpdateFilePage(username: String, repoName: String, path: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    githubService.getExistingFileForUpdating(username, repoName, path).map {
      case Right(existingFile) => Ok(views.html.createNewFilePage(username, repoName, path, existingFile.sha)("update")(FileForm.fileForm.fill(FileForm(existingFile.fileName, existingFile.content))))
      case Left(error) => Ok(views.html.notFound(s"$username/$repoName file for updating")(s"${error.httpResponseStatus}: ${error.reason}"))
    }
  }

  def createNewFile(username: String, repoName: String, path: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    println("--------------createNewFile------------")
    FileForm.fileForm.bindFromRequest.fold(
      formWithErrors => {
        println(s"--------------Error: ${formWithErrors.errors.toString()} ------------")
        Future(Ok(views.html.createNewFilePage(username, repoName, path, "")("create")(formWithErrors)))
      },
      outputFile => {
        println(s"--------------Folded: $outputFile, ${outputFile.fileName}, ${outputFile.fileContent} ------------")
        githubService.createNewFile(username, repoName, path, outputFile.fileName, outputFile.fileContent).map {
          case Right(value) => Redirect(controllers.routes.HomeController.getUserRepositoryContents(username, repoName, path))
          case Left(error) => Ok(views.html.notFound(s"$username/$repoName contents")(s"${error.httpResponseStatus}: ${error.reason}"))
        }
      }
    )
  }


  def updateFile(username: String, repoName: String, path: String, sha: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    println("--------------updating file------------")
    FileForm.fileForm.bindFromRequest.fold(
      formWithErrors => {
        println(s"--------------Error: ${formWithErrors.errors.toString()} ------------")
        Future(Ok(views.html.createNewFilePage(username, repoName, path, sha)("update")(formWithErrors)))
      },
      outputFile => {
        println(s"--------------Folded: $outputFile, ${outputFile.fileName}, ${outputFile.fileContent} ------------")
        githubService.updateFile(username, repoName, path, outputFile.fileName, outputFile.fileContent, sha).map {
          case Right(value) => Redirect(controllers.routes.HomeController.getFileContents(username, repoName, path))
          case Left(error) => Ok(views.html.notFound(s"$username/$repoName contents")(s"${error.httpResponseStatus}: ${error.reason}"))
        }
      }
    )
  }
}

