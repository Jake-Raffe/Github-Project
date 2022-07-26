package services

import cats.data.EitherT
import com.google.common.io.BaseEncoding.base64
import connectors.GithubConnector
import models.APIError.BadAPIResponse
import models.Content.formats
import models.{APIError, Content, ExistingFile, FileContent, FileForm, Repository, User}
import play.api.libs.json.{JsError, JsLookupResult, JsSuccess, Json}
import play.api.mvc.Request
import repositories.DataRepository

import scala.sys.env
//import sun.misc.BASE64Decoder
import java.nio.ByteBuffer

import java.nio.charset.StandardCharsets
import java.util.Base64
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Left

class GithubService @Inject()(connector: GithubConnector){
import GithubService._

  def getUser(username: String)(implicit ec: ExecutionContext): Future[Either[APIError, User]] =
    connector.getUser[User](username)

  def getUserRepo(username: String)(implicit ec: ExecutionContext): Future[Either[APIError, List[Repository]]] =
    connector.getRepoList[Repository](username)

  def getRepoContents(username: String, repoName: String, path: String)(implicit ec: ExecutionContext): Future[Either[APIError, List[Content]]] =
    connector.getRepoContent[Content](username, repoName, path)

  def getFileContents(username: String, repoName: String, path: String)(implicit ec: ExecutionContext): Future[Either[APIError, String]] = {
    connector.getFileContents[FileContent](username, repoName, path).map{
      case Right(encoded) => {
        val decodedContent = decodeBase64(encoded)
        Right(decodedContent)
      }
      case Left(err) => Left(err)
    }
  }

  def getExistingFileForUpdating(username: String, repoName: String, path: String)(implicit ec: ExecutionContext): Future[Either[APIError, ExistingFile]] = {
    connector.getFileNameContentsSha[ExistingFile](username, repoName, path).map {
      case Right(existingFile) => {
        val decodedContent = decodeBase64(existingFile.content)
        Right(ExistingFile(existingFile.fileName, decodedContent, existingFile.sha))
      }
      case Left(err) => Left(err)
    }
  }

  def createNewFile(username: String, repoName: String, path: String, fileName: String, fileContent: String)(implicit ex: ExecutionContext): Future[Either[APIError, String]] = {
    val encodedContent = encodeBase64(fileContent)
    val newPath = path.replace("/", "")
    println(s"----- path: $path, ------ $newPath")
    connector.createNewFile(username, repoName, newPath, fileName, encodedContent) map {
      case Right(string: String) => Right(string)
      case Left(error: APIError) => Left(error)
    }
  }

  def updateFile(username: String, repoName: String, path: String, fileName: String, fileContent: String, sha: String)(implicit ex: ExecutionContext): Future[Either[APIError, String]] = {
    println(path)
    val encodedContent = encodeBase64(fileContent)
    connector.updateFile(username, repoName, path, fileName, encodedContent, sha) map {
      case Right(string: String) => Right(string)
      case Left(error: APIError) => Left(error)
    }
  }

}


object GithubService {
  def decodeBase64(inputBase64: String): String = {
    val byteArray = inputBase64.getBytes(StandardCharsets.UTF_8)
    val decodedFromBase64 = Base64.getMimeDecoder.decode(byteArray)
    val convertedToString = new String(decodedFromBase64, StandardCharsets.UTF_8)
    convertedToString
  }
  def encodeBase64(inputString: String): String = {
    val byteArray = inputString.getBytes(StandardCharsets.UTF_8)
    val encoded = Base64.getMimeEncoder.encodeToString(byteArray)
    encoded
  }
}
