
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._
/*1.2*/import models.{User,Content, CreatedFile, FileForm}
/*3.2*/import helper._
/*4.2*/import play.api.data.Form
/*5.2*/import play.api.data.Forms._

object createNewFilePage extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[String,String,String,String,Form[FileForm],Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*7.2*/(username: String, repoName: String, path: String)(purpose: String)(fileForm: Form[FileForm])(implicit messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.1*/("""



"""),format.raw/*12.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
    <title>"""),_display_(/*15.13*/repoName),format.raw/*15.21*/("""</title>

</head>
<body>

    <h3>Github</h3>
    <form>
        <label for="search">Search username...</label>
        <input type="search" name="search" id="search">
    </form>
    <a href="""),_display_(/*25.14*/{controllers.routes.HomeController.getUser(username)}),format.raw/*25.67*/(""">Back to """),_display_(/*25.77*/username),format.raw/*25.85*/("""</a>
    <a href="""),_display_(/*26.14*/{controllers.routes.HomeController.getUserRepositories(username)}),format.raw/*26.79*/(""">Back to Repositories</a>
    <hr>
    <h1>"""),_display_(/*28.10*/{if (purpose == "create") "Create new file in directory: @repoName@path"
            else if (purpose == "edit") "Update file: @repoName@path"
            else "How did you get here?"
        }),format.raw/*31.10*/("""
    """),format.raw/*32.5*/("""</h1>

    <section>
        """),_display_(/*35.10*/form(action = controllers.routes.HomeController.createNewFile(username, repoName, ""))/*35.96*/ {_display_(Seq[Any](format.raw/*35.98*/("""
            """),_display_(/*36.14*/inputText(fileForm("fileName"))),format.raw/*36.45*/("""
            """),_display_(/*37.14*/inputText(fileForm("fileContent"))),format.raw/*37.48*/("""
            """),format.raw/*38.13*/("""<input type="submit" value="Submit">
        """)))}),format.raw/*39.10*/("""
    """),format.raw/*40.5*/("""</section>

</body>
</html>
"""))
      }
    }
  }

  def render(username:String,repoName:String,path:String,purpose:String,fileForm:Form[FileForm],messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(username,repoName,path)(purpose)(fileForm)(messages)

  def f:((String,String,String) => (String) => (Form[FileForm]) => (Messages) => play.twirl.api.HtmlFormat.Appendable) = (username,repoName,path) => (purpose) => (fileForm) => (messages) => apply(username,repoName,path)(purpose)(fileForm)(messages)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2022-07-21T12:23:53.689093
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/createNewFilePage.scala.html
                  HASH: a1403e1ab471546ba995badf3ce96692159c7257
                  MATRIX: 432->1|491->55|514->72|547->99|937->130|1153->253|1184->257|1263->309|1292->317|1512->510|1586->563|1623->573|1652->581|1697->599|1783->664|1854->708|2068->901|2100->906|2157->936|2252->1022|2292->1024|2333->1038|2385->1069|2426->1083|2481->1117|2522->1130|2599->1176|2631->1181
                  LINES: 17->1|18->3|19->4|20->5|25->7|30->8|34->12|37->15|37->15|47->25|47->25|47->25|47->25|48->26|48->26|50->28|53->31|54->32|57->35|57->35|57->35|58->36|58->36|59->37|59->37|60->38|61->39|62->40
                  -- GENERATED --
              */
          