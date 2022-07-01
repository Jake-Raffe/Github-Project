package models

import akka.http.javadsl.model.headers.Location
import play.api.libs.json.{Json, OFormat}

import java.time.LocalDate

case class User(username: String,
                dateCreated: LocalDate,
                location: Location,
                noOfFollowers: Int,
                noFollowing: Int)

object User {
  implicit val formats: OFormat[User] = Json.format[User]
}
