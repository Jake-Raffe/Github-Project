package models

import akka.http.javadsl.model.headers.Location
import play.api.libs.json.{Json, OFormat}

import java.time.LocalDate

case class Repository(id: String, name: String)
case class RepositoryList(repoList: List[Repository])

object RepositoryList {
  implicit val formats: OFormat[RepositoryList] = Json.format[RepositoryList]
}
object Repository {
  implicit val formats: OFormat[Repository] = Json.format[Repository]
}