package models

import play.api.libs.json.{Json, OFormat}

//case class CreatedFile(owner: String, repo: String, path: String, message: String, content: String, branch: String)
case class CreatedFile(message: String, content: String, branch: String)
// PATH PARAMS:
// owner = owner of repo
// path = path
// BODY PARAMS:
// message = commit message
// content = updated file content (Base64 encoded)
// branch = git branch name for commit
object CreatedFile {
  implicit val formats: OFormat[CreatedFile] = Json.format[CreatedFile]
}