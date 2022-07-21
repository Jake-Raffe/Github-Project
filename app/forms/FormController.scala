package forms

import models.{CreatedFile, FileContent}
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, MessagesActionBuilder, MessagesRequest, Request}

import javax.inject.Inject

class FormController @Inject() (messagesAction: MessagesActionBuilder, components: ControllerComponents)
  extends AbstractController(components) with play.api.i18n.I18nSupport {

//  def forms() = Action { implicit request: Request[AnyContent] =>
//    Ok(views.html.userRepoContentsPage(username)(repoName)(contents)(newFileForm.fileForm))
//  }

//  val fileForm: Form[FormFileTemplate] = Form(
//    mapping(
//      "fileName" -> nonEmptyText,
//      "fileContent" -> text,
//  (views.html.userRepoContentsPage.apply)(views.html.userRepoContentsPage.unapply)
//    )
//  )
//
//  def index = messagesAction { implicit request: MessagesRequest[AnyContent] =>
//    Ok(views.html.messages(userForm))
//  }
//
//  def post = TODO

}
