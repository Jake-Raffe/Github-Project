package services

import cats.data.EitherT
import connectors.GithubConnector
import models.{APIError, RepositoryList, User}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class GithubService @Inject()(connector: GithubConnector){

  def getUser(urlOverride: Option[String] = None, username: String)(implicit ec: ExecutionContext): EitherT[Future, APIError, User] =
    connector.get[User](urlOverride.getOrElse(s"https://api.github.com/users/${username}"))

  def getUserRepo(urlOverride: Option[String] = None, username: String)(implicit ec: ExecutionContext): EitherT[Future, APIError, RepositoryList] =
    connector.get[RepositoryList](urlOverride.getOrElse(s"https://api.github.com/users/${username}/repos"))



}
