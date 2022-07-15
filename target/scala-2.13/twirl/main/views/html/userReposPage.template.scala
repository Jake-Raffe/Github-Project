
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
/*1.2*/import models.{User,Repository}

object userReposPage extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,List[Repository],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(username: String)(repoList: List[Repository]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),format.raw/*4.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
    <title>"""),_display_(/*7.13*/username),format.raw/*7.21*/(""" """),format.raw/*7.22*/("""repositories</title>

</head>
<body>
    <h3>Github</h3>
    <a href="""),_display_(/*12.14*/{controllers.routes.HomeController.getUser(username)}),format.raw/*12.67*/(""">Back to """),_display_(/*12.77*/username),format.raw/*12.85*/("""</a>
    <hr>
    <h1>"""),_display_(/*14.10*/username),format.raw/*14.18*/("""'s repositories:</h1>
    <section>
        <p>-----------------------</p>
        """),_display_(/*17.10*/repoList/*17.18*/.map/*17.22*/ { repo =>_display_(Seq[Any](format.raw/*17.32*/("""
            """),format.raw/*18.13*/("""<element>
                <h3>"""),_display_(/*19.22*/repo/*19.26*/.name),format.raw/*19.31*/("""</h3>
                <a href="""),_display_(/*20.26*/controllers/*20.37*/.routes.HomeController.getUserRepositoryContents(username, repo.name)),format.raw/*20.106*/(""">Open contents</a>
            </element>
            <p>-----------------------</p>
        """)))}),format.raw/*23.10*/("""
    """),format.raw/*24.5*/("""</section>


</body>
</html>
"""))
      }
    }
  }

  def render(username:String,repoList:List[Repository]): play.twirl.api.HtmlFormat.Appendable = apply(username)(repoList)

  def f:((String) => (List[Repository]) => play.twirl.api.HtmlFormat.Appendable) = (username) => (repoList) => apply(username)(repoList)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2022-07-15T10:49:52.456862
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/userReposPage.scala.html
                  HASH: 651f158e322d16b67ec78b977150e5d2dd0e98df
                  MATRIX: 432->1|793->34|933->81|960->82|1038->134|1066->142|1094->143|1191->213|1265->266|1302->276|1331->284|1381->307|1410->315|1521->399|1538->407|1551->411|1599->421|1640->434|1698->465|1711->469|1737->474|1795->505|1815->516|1906->585|2031->679|2063->684
                  LINES: 17->1|22->2|27->3|28->4|31->7|31->7|31->7|36->12|36->12|36->12|36->12|38->14|38->14|41->17|41->17|41->17|41->17|42->18|43->19|43->19|43->19|44->20|44->20|44->20|47->23|48->24
                  -- GENERATED --
              */
          