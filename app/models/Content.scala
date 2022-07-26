package models

import play.api.libs.json.{Json, OFormat}

case class Content(name: String, `type`: String, path: String)
object Content {
  implicit val formats: OFormat[Content] = Json.format[Content]
}
