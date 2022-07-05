package services

import cats.data.EitherT
import connectors.GithubConnector
import models.{APIError, Repository, RepositoryList, User}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class GithubService @Inject()(connector: GithubConnector){

  def getUser(username: String)(implicit ec: ExecutionContext): Future[Either[APIError, User]] =
    connector.getUser[User](s"https://api.github.com/users/${username}")

  def getUserRepo(urlOverride: Option[String] = None, username: String)(implicit ec: ExecutionContext): EitherT[Future, APIError, List[Repository]] =
    connector.getRepoList[RepositoryList](urlOverride.getOrElse(s"https://api.github.com/users/${username}/repos"))

//  def getAllUsers(urlOverride: Option[String] = None)(implicit ec: ExecutionContext): EitherT[Future, APIError, Seq[User]] =
//    connector.getAllUsers[Seq[User]](urlOverride.getOrElse("https://api.github.com/users"))
//

}
