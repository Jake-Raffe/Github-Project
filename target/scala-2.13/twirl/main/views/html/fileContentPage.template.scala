
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
/*1.2*/import models.{User,Repository, FileContent}
/*2.2*/import java.util.Base64
/*3.2*/import java.nio.charset.StandardCharsets

object fileContentPage extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[String,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*4.2*/(username: String, repoName: String, path: String)(fileContent: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""
"""),format.raw/*6.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
    <title>"""),_display_(/*9.13*/repoName),_display_(/*9.22*/path),format.raw/*9.26*/("""</title>

</head>
<body>
    <h3>Github</h3>
    <a href="""),_display_(/*14.14*/{controllers.routes.HomeController.getUser(username)}),format.raw/*14.67*/(""">Back to """),_display_(/*14.77*/username),format.raw/*14.85*/("""</a>
    <a href="""),_display_(/*15.14*/{controllers.routes.HomeController.getUserRepositories(username)}),format.raw/*15.79*/(""">Back to Repositories</a>
    <a href="""),_display_(/*16.14*/{controllers.routes.HomeController.getUserRepositoryContents(username, repoName, "/")}),format.raw/*16.100*/(""">Back to """),_display_(/*16.110*/repoName),format.raw/*16.118*/("""</a>
    <hr>
    <h1>"""),_display_(/*18.10*/repoName),format.raw/*18.18*/("""/"""),_display_(/*18.20*/path),format.raw/*18.24*/(""" """),format.raw/*18.25*/("""contents:</h1>

    <section>
        <p>-----------------------</p>
        <pre>"""),_display_(/*22.15*/Html(fileContent)),format.raw/*22.32*/("""</pre>
        <p>-----------------------</p>
    </section>

    <a href="""),_display_(/*26.14*/{controllers.routes.HomeController.openUpdateFilePage(username, repoName, path)}),format.raw/*26.94*/(""">Update file</a>

</body>
</html>
"""))
      }
    }
  }

  def render(username:String,repoName:String,path:String,fileContent:String): play.twirl.api.HtmlFormat.Appendable = apply(username,repoName,path)(fileContent)

  def f:((String,String,String) => (String) => play.twirl.api.HtmlFormat.Appendable) = (username,repoName,path) => (fileContent) => apply(username,repoName,path)(fileContent)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2022-07-26T10:50:56.722111
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/fileContentPage.scala.html
                  HASH: a92b772c6385654d6c70235fed1593ae32eb4146
                  MATRIX: 432->1|484->47|515->72|891->114|1056->186|1083->187|1161->239|1189->248|1213->252|1298->310|1372->363|1409->373|1438->381|1483->399|1569->464|1635->503|1743->589|1781->599|1811->607|1861->630|1890->638|1919->640|1944->644|1973->645|2083->728|2121->745|2223->820|2324->900
                  LINES: 17->1|18->2|19->3|24->4|29->5|30->6|33->9|33->9|33->9|38->14|38->14|38->14|38->14|39->15|39->15|40->16|40->16|40->16|40->16|42->18|42->18|42->18|42->18|42->18|46->22|46->22|50->26|50->26
                  -- GENERATED --
              */
          