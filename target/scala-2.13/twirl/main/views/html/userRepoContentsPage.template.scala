
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
    <title>"""),_display_(/*7.13*/username),format.raw/*7.21*/("""'s """),_display_(/*7.25*/repoName),format.raw/*7.33*/("""</title>

</head>
<body>

    <h1>"""),_display_(/*12.10*/repoName),format.raw/*12.18*/(""" """),format.raw/*12.19*/("""contents:</h1>

    <section>
        """),_display_(/*15.10*/contents/*15.18*/.map/*15.22*/ { file =>_display_(Seq[Any](format.raw/*15.32*/("""
            """),format.raw/*16.13*/("""<element>
                <p>"""),_display_(/*17.21*/file/*17.25*/.name),format.raw/*17.30*/("""</p>
                """),format.raw/*21.19*/("""

                """),format.raw/*23.17*/("""<a href="""),_display_(/*23.26*/controllers/*23.37*/.routes.HomeController.getUserRepositoryContentsPath(username, repoName, s"/${file.name}")),format.raw/*23.127*/(""">Open</a>
            </element>
            <p>-----------------------</p>
        """)))}),format.raw/*26.10*/("""
    """),format.raw/*27.5*/("""</section>

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
                  DATE: 2022-07-07T16:51:36.699404
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/userRepoContentsPage.scala.html
                  HASH: be691b33f9e1878f9bbde6cf1f12b8c8d63c6439
                  MATRIX: 432->1|801->31|956->93|983->94|1061->146|1089->154|1119->158|1147->166|1209->201|1238->209|1267->210|1333->249|1350->257|1363->261|1411->271|1452->284|1509->314|1522->318|1548->323|1597->440|1643->458|1679->467|1699->478|1811->568|1927->653|1959->658
                  LINES: 17->1|22->2|27->3|28->4|31->7|31->7|31->7|31->7|36->12|36->12|36->12|39->15|39->15|39->15|39->15|40->16|41->17|41->17|41->17|42->21|44->23|44->23|44->23|44->23|47->26|48->27
                  -- GENERATED --
              */
          