package models

import play.api.libs.json.{Json, OFormat}
import akka.http.scaladsl.model.DateTime
import akka.http.javadsl.model.headers.Location

case class GitUser(login: String, created_at: DateTime, location: Location, followers: Int, following: Int)

//object GitUser {
//  implicit val formats: OFormat[GitUser] = Json.format[GitUser]
//}