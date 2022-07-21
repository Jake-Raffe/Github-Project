package models

import play.api.libs.json.{Json, OFormat}


case class Repository(name: String)

object Repository {
  implicit val formats: OFormat[Repository] = Json.format[Repository]
}