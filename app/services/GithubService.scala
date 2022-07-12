package services

import cats.data.EitherT
import com.google.common.io.BaseEncoding.base64
import controllers.connectors.GithubConnector
import models.{APIError, Content, FileContent, Repository, User}
import play.api.libs.json.{JsError, JsLookupResult, JsSuccess, Json}
import play.api.mvc.Request
import repositories.DataRepository
//import sun.misc.BASE64Decoder
import java.nio.ByteBuffer

import java.nio.charset.StandardCharsets
import java.util.Base64
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Left

class GithubService @Inject()(connector: GithubConnector, dataRepository: DataRepository){
import GithubService._

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
        val decodedContent = decodeBase64(encoded)
//        println("------> " + byteArray)
//        val correctedArray = byteArray
//          .map {
//          case '-' => '+'
//          case '_' => '/'
//          case c => c
//          }
//          .map(item => item.toByte)
//        println("------> " + correctedArray.toString)
        Right(decodedContent)
      }
      case Left(err) => Left(err)
    }
  }
}

object GithubService {
  def decodeBase64(inputBase64: String): String = {
    val byteArray = inputBase64.getBytes(StandardCharsets.UTF_8)
    val decodedFromBase64 = Base64.getMimeDecoder.decode(byteArray)
    val convertedToString = new String(decodedFromBase64, StandardCharsets.UTF_8)
    println(convertedToString)
    convertedToString
  }
}
