package models

import play.api.libs.json.{Json, OFormat}


case class UpdateField(login: String,
                       fieldName: String,
                       edit: String)

object UpdateField {
  implicit val formats: OFormat[UpdateField] = Json.format[UpdateField]
}