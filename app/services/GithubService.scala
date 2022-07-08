package services

import cats.data.EitherT
import connectors.GithubConnector
import models.{APIError, Content, FileContent, Repository, User}
import play.api.libs.json.{JsLookupResult, Json}
import play.api.mvc.Request
import repositories.DataRepository

import java.nio.charset.StandardCharsets
import java.util.Base64
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Left

class GithubService @Inject()(connector: GithubConnector, dataRepository: DataRepository){

  def getUser(username: String)(implicit ec: ExecutionContext): Future[Either[APIError, User]] =
    connector.getUser[User](s"https://api.github.com/users/${username}")

  def getAndAddUser(username: String)(implicit ec: ExecutionContext): Future[Either[APIError, User]] = {
    getUser(username).flatMap {
      case Right(gotUser) => dataRepository.create(gotUser)
      case Left(getUserError) => Future(Left(APIError.BadAPIResponse(400, "Could not find book")))
    }
  }

  def getUserRepo(username: String)(implicit ec: ExecutionContext): Future[Either[APIError, List[Repository]]] =
    connector.getRepoList[Repository](s"https://api.github.com/users/${username}/repos")

  def getRepoContents(username: String, repoName: String)(implicit ec: ExecutionContext): Future[Either[APIError, List[Content]]] =
    connector.getRepoContent[Content](s"https://api.github.com/repos/${username}/${repoName}/contents")

  def getRepoContentsPath(username: String, repoName: String, path: String)(implicit ec: ExecutionContext): Future[Either[APIError, List[Content]]] =
    connector.getRepoContentDeeper[Content](s"https://api.github.com/repos/${username}/${repoName}/contents$path")

  def getFileContents(username: String, repoName: String, path: String)(implicit ec: ExecutionContext): Future[Either[APIError, String]] = {
    connector.getFileContents[FileContent](s"https://api.github.com/repos/${username}/${repoName}/contents$path").map{
      case Right(encoded) => {
        val decoded = Base64.getDecoder().decode(encoded.bytecode)
        Right(new String(decoded, StandardCharsets.UTF_8))
      }
    }
  }

}
