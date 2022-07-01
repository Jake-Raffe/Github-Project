package models

import akka.http.javadsl.model.headers.Location
import play.api.libs.json.{Json, OFormat}

import java.time.LocalDate

case class RepositoryList(username: String,
                          dateCreated: LocalDate,
                          location: Location,
                          noOfFollowers: Int,
                          noFollowing: Int)

object RepositoryList {
  implicit val formats: OFormat[RepositoryList] = Json.format[RepositoryList]
}
