package models

import play.api.libs.json.{Json, OFormat}

case class Content(name: String, `type`: String, path: String, sha: String)
object Content {
  implicit val formats: OFormat[Content] = Json.format[Content]
}
