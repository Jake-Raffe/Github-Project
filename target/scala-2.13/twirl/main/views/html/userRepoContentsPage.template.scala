
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
/*2.2*/import helper._

object userRepoContentsPage extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[String,String,List[Content],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*3.2*/(username: String, repoName: String, contents: List[Content]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""


"""),format.raw/*7.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
    <title>"""),_display_(/*10.13*/repoName),format.raw/*10.21*/("""</title>

</head>
<body>

    <h3>Github</h3>
    <a href="""),_display_(/*16.14*/{controllers.routes.HomeController.getUser(username)}),format.raw/*16.67*/(""">Back to """),_display_(/*16.77*/username),format.raw/*16.85*/("""</a>
    <a href="""),_display_(/*17.14*/{controllers.routes.HomeController.getUserRepositories(username)}),format.raw/*17.79*/(""">Back to Repositories</a>
    <hr>
    <h1>"""),_display_(/*19.10*/repoName),format.raw/*19.18*/(""" """),format.raw/*19.19*/("""contents:</h1>

    <section>
        <p>-----------------------</p>
        """),_display_(/*23.10*/contents/*23.18*/.map/*23.22*/ { file =>_display_(Seq[Any](format.raw/*23.32*/("""
            """),format.raw/*24.13*/("""<element>
                <h4>"""),_display_(/*25.22*/file/*25.26*/.name),format.raw/*25.31*/("""</h4>
                <p>"""),_display_(/*26.21*/{ if (file.`type`.contains("dir")) "Folder" else if(file.`type`.contains("file")) "File" else "Other"}),format.raw/*26.123*/("""</p>

                <a href="""),_display_(/*28.26*/{
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

    <a href="""),_display_(/*41.14*/{controllers.routes.HomeController.openNewFilePage(username, repoName, "")}),format.raw/*41.89*/(""">Create new file</a>

</body>
</html>
"""))
      }
    }
  }

  def render(username:String,repoName:String,contents:List[Content]): play.twirl.api.HtmlFormat.Appendable = apply(username,repoName,contents)

  def f:((String,String,List[Content]) => play.twirl.api.HtmlFormat.Appendable) = (username,repoName,contents) => apply(username,repoName,contents)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2022-07-21T12:47:07.605170
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/userRepoContentsPage.scala.html
                  HASH: 1c1c818a7b6c33f9bdd55123b60153a02a461acb
                  MATRIX: 432->1|468->31|824->48|979->110|1008->113|1087->165|1116->173|1202->232|1276->285|1313->295|1342->303|1387->321|1473->386|1544->430|1573->438|1602->439|1707->517|1724->525|1737->529|1785->539|1826->552|1884->583|1897->587|1923->592|1976->618|2100->720|2158->751|2546->1118|2596->1141|2729->1252|2774->1269|2885->1349|2917->1354|2969->1379|3065->1454
                  LINES: 17->1|18->2|23->3|28->4|31->7|34->10|34->10|40->16|40->16|40->16|40->16|41->17|41->17|43->19|43->19|43->19|47->23|47->23|47->23|47->23|48->24|49->25|49->25|49->25|50->26|50->26|52->28|57->33|58->34|58->34|59->35|62->38|63->39|65->41|65->41
                  -- GENERATED --
              */
          