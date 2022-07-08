package models

import play.api.libs.json.{Json, OFormat}

case class Content(name: String, `type`: String)
object Content {
  implicit val formats: OFormat[Content] = Json.format[Content]
}
//case class Content(name: String, contentType: String, size: String)
//object Content {
//  implicit val formats: OFormat[Content] = Json.format[Content]
//}
//case class ContentL(name: String, contentType: String, size: Long)
//object ContentL {
//  implicit val formats: OFormat[ContentL] = Json.format[ContentL]
//}