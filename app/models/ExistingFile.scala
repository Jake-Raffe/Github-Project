package models

import play.api.libs.json.{Json, OFormat}

case class ExistingFile(fileName: String, content: String, sha: String)
object ExistingFile {
  implicit val formats: OFormat[ExistingFile] = Json.format[ExistingFile]
}

