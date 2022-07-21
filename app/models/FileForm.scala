package models

import play.api.data.Form
import play.api.data.Forms._

case class FileForm(fileName: String, fileContent: String)

object FileForm {
  val fileForm: Form[FileForm] = Form(
    mapping(
      "fileName" -> nonEmptyText,
      "fileContent" -> text
    )(FileForm.apply)(FileForm.unapply)
  )
}

