package models

import play.api.libs.json.{Json, OFormat}


case class Repository(name: String)
case class RepositoryList(repoList: List[Repository])

object RepositoryList {
  implicit val formats: OFormat[RepositoryList] = Json.format[RepositoryList]
}
object Repository {
  implicit val formats: OFormat[Repository] = Json.format[Repository]
}