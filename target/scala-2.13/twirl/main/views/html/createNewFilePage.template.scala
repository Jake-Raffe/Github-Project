
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
/*6.2*/import java.net.URLEncoder

object createNewFilePage extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[String,String,String,String,String,Form[FileForm],Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*8.2*/(username: String, repoName: String, path: String, sha: String)(purpose: String)(fileForm: Form[FileForm])(implicit messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*9.1*/("""



"""),format.raw/*13.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
    <title>"""),_display_(/*16.13*/repoName),format.raw/*16.21*/("""</title>

</head>
<body>

    <h3>Github</h3>
    <form>
        <label for="search">Search username...</label>
        <input type="search" name="search" id="search">
    </form>
    <a href="""),_display_(/*26.14*/{controllers.routes.HomeController.getUser(username)}),format.raw/*26.67*/(""">Back to """),_display_(/*26.77*/username),format.raw/*26.85*/("""</a>
    <a href="""),_display_(/*27.14*/{controllers.routes.HomeController.getUserRepositories(username)}),format.raw/*27.79*/(""">Back to Repositories</a>
    <a href="""),_display_(/*28.14*/{controllers.routes.HomeController.getUserRepositoryContents(username, repoName, "repo-contents")}),format.raw/*28.112*/(""">Back to """),_display_(/*28.122*/repoName),format.raw/*28.130*/("""</a>
    <hr>
    <h1>"""),_display_(/*30.10*/{if (purpose == "create") s"Create new file in directory: $repoName$path"
            else if (purpose == "update") s"Update file: $repoName$path"
            else "How did you get here?"
        }),format.raw/*33.10*/("""
    """),format.raw/*34.5*/("""</h1>

    <section>
        """),_display_(/*37.10*/form(action = {
                            if (purpose == "create") controllers.routes.HomeController.createNewFile(username, repoName, if(path.equals("")) "top" else path)
                            else if (purpose == "update") controllers.routes.HomeController.updateFile(username, repoName, path, sha)
                            else controllers.routes.HomeController.getUser(username)
                        }
        )/*42.10*/ {_display_(Seq[Any](format.raw/*42.12*/("""
            """),_display_(/*43.14*/inputText(fileForm("fileName"))),format.raw/*43.45*/("""
            """),_display_(/*44.14*/textarea(fileForm("fileContent"))),format.raw/*44.47*/("""
            """),format.raw/*45.13*/("""<input type="submit" value="Submit">
        """)))}),format.raw/*46.10*/("""
    """),format.raw/*47.5*/("""</section>

</body>
</html>
"""))
      }
    }
  }

  def render(username:String,repoName:String,path:String,sha:String,purpose:String,fileForm:Form[FileForm],messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(username,repoName,path,sha)(purpose)(fileForm)(messages)

  def f:((String,String,String,String) => (String) => (Form[FileForm]) => (Messages) => play.twirl.api.HtmlFormat.Appendable) = (username,repoName,path,sha) => (purpose) => (fileForm) => (messages) => apply(username,repoName,path,sha)(purpose)(fileForm)(messages)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2022-07-27T15:05:24.457968
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/createNewFilePage.scala.html
                  HASH: 2029705562596b661cb2de0da98e4c2eddd15fc1
                  MATRIX: 432->1|491->55|514->72|547->99|583->129|978->158|1207->294|1238->298|1317->350|1346->358|1566->551|1640->604|1677->614|1706->622|1751->640|1837->705|1903->744|2023->842|2061->852|2091->860|2141->883|2359->1080|2391->1085|2448->1115|2885->1543|2925->1545|2966->1559|3018->1590|3059->1604|3113->1637|3154->1650|3231->1696|3263->1701
                  LINES: 17->1|18->3|19->4|20->5|21->6|26->8|31->9|35->13|38->16|38->16|48->26|48->26|48->26|48->26|49->27|49->27|50->28|50->28|50->28|50->28|52->30|55->33|56->34|59->37|64->42|64->42|65->43|65->43|66->44|66->44|67->45|68->46|69->47
                  -- GENERATED --
              */
          