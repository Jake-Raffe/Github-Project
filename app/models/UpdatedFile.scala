package models

import play.api.libs.json.{Json, OFormat}

case class UpdatedFile(path: String, message: String, content: String, sha: String, branch: String)
// path = path
// message = commit message
// content == updated file content (Base64 encoded)
// sha = sha code of original file
// branch = git branch name for commit
object UpdatedFile {
  implicit val formats: OFormat[UpdatedFile] = Json.format[UpdatedFile]
}