package services

import cats.data.EitherT
import connectors.GithubConnector
import models.{APIError, Repository, User}
import play.api.libs.json.Json
import play.api.mvc.Request
import repositories.DataRepository

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

//  def getAllUsers(urlOverride: Option[String] = None)(implicit ec: ExecutionContext): EitherT[Future, APIError, Seq[User]] =
//    connector.getAllUsers[Seq[User]](urlOverride.getOrElse("https://api.github.com/users"))
//

}
