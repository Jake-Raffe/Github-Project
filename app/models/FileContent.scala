package models

import play.api.libs.json.{Json, OFormat}
import play.mvc.BodyParser.Raw

case class FileContent(content: String)
object FileContent {
  implicit val formats: OFormat[FileContent] = Json.format[FileContent]
}