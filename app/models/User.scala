package models

import akka.http.javadsl.model.headers.Location
import akka.http.scaladsl.model.DateTime
import play.api.libs.json.{Json, OFormat}

import java.time.LocalDate

case class User(login: String, created_at: String, location: Option[String], followers: Int, following: Int)

object User {
  implicit val formats: OFormat[User] = Json.format[User]
}
