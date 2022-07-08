package models

import play.api.libs.json.{Json, OFormat}

case class FileContent(bytecode: String)
object FileContent {
  implicit val formats: OFormat[FileContent] = Json.format[FileContent]
}