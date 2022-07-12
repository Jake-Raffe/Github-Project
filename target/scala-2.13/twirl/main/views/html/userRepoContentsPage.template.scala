
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
/*1.2*/import models.{User,Content}

object userRepoContentsPage extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[String,String,List[Content],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(username: String)(repoName: String)(contents: List[Content]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),format.raw/*4.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
    <title>"""),_display_(/*7.13*/repoName),format.raw/*7.21*/("""</title>

</head>
<body>

    <h3>Github</h3>
    <a href="""),_display_(/*13.14*/{controllers.routes.HomeController.getUser(username)}),format.raw/*13.67*/(""">Back to """),_display_(/*13.77*/username),format.raw/*13.85*/("""</a>
    <a href="""),_display_(/*14.14*/{controllers.routes.HomeController.getUserRepositories(username)}),format.raw/*14.79*/(""">Back to Repositories</a>
    <hr>
    <h1>"""),_display_(/*16.10*/repoName),format.raw/*16.18*/(""" """),format.raw/*16.19*/("""contents:</h1>

    <section>
        <p>-----------------------</p>
        """),_display_(/*20.10*/contents/*20.18*/.map/*20.22*/ { file =>_display_(Seq[Any](format.raw/*20.32*/("""
            """),format.raw/*21.13*/("""<element>
                <h4>"""),_display_(/*22.22*/file/*22.26*/.name),format.raw/*22.31*/("""</h4>
                <p>"""),_display_(/*23.21*/{ if (file.`type`.contains("dir")) "Folder" else if(file.`type`.contains("file")) "File" else "Other"}),format.raw/*23.123*/("""</p>
                """),format.raw/*26.19*/("""

                """),format.raw/*28.17*/("""<a href="""),_display_(/*28.26*/{
                   if (file.`type`.contains("dir"))
                        controllers.routes.HomeController.getUserRepositoryContentsPath(username, repoName, s"/${file.name}")
                   else if (file.`type`.contains("file"))
                        controllers.routes.HomeController.getFileContents(username, repoName, s"/${file.name}")
                }),format.raw/*33.18*/(""">
                    """),_display_(/*34.22*/{ if (file.`type`.contains("dir")) "Open folder" else if(file.`type`.contains("file")) "Open file" else "Open"}),format.raw/*34.133*/("""
                """),format.raw/*35.17*/("""</a>
            </element>
            <p>-----------------------</p>
        """)))}),format.raw/*38.10*/("""
    """),format.raw/*39.5*/("""</section>

</body>
</html>
"""))
      }
    }
  }

  def render(username:String,repoName:String,contents:List[Content]): play.twirl.api.HtmlFormat.Appendable = apply(username)(repoName)(contents)

  def f:((String) => (String) => (List[Content]) => play.twirl.api.HtmlFormat.Appendable) = (username) => (repoName) => (contents) => apply(username)(repoName)(contents)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2022-07-12T15:37:25.247895
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/userRepoContentsPage.scala.html
                  HASH: 59828ef10d22b02de29b4114e59b7154769cb482
                  MATRIX: 432->1|801->31|956->93|983->94|1061->146|1089->154|1175->213|1249->266|1286->276|1315->284|1360->302|1446->367|1517->411|1546->419|1575->420|1680->498|1697->506|1710->510|1758->520|1799->533|1857->564|1870->568|1896->573|1949->599|2073->701|2122->777|2168->795|2204->804|2592->1171|2642->1194|2775->1305|2820->1322|2931->1402|2963->1407
                  LINES: 17->1|22->2|27->3|28->4|31->7|31->7|37->13|37->13|37->13|37->13|38->14|38->14|40->16|40->16|40->16|44->20|44->20|44->20|44->20|45->21|46->22|46->22|46->22|47->23|47->23|48->26|50->28|50->28|55->33|56->34|56->34|57->35|60->38|61->39
                  -- GENERATED --
              */
          