
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

    <h1>These are """),_display_(/*12.20*/username),format.raw/*12.28*/("""'s repositories</h1>

    <section>
        """),_display_(/*15.10*/repoList/*15.18*/.map/*15.22*/ { repo =>_display_(Seq[Any](format.raw/*15.32*/("""
            """),format.raw/*16.13*/("""<element>
                <h3>"""),_display_(/*17.22*/repo/*17.26*/.name),format.raw/*17.31*/("""</h3>
                <a href="""),_display_(/*18.26*/controllers/*18.37*/.routes.HomeController.getUserRepositoryContents(username, repo.name)),format.raw/*18.106*/(""">Contents</a>
            </element>
            <p>-----------------------</p>
        """)))}),format.raw/*21.10*/("""
    """),format.raw/*22.5*/("""</section>


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
                  DATE: 2022-07-08T11:45:24.575271
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/userReposPage.scala.html
                  HASH: bebb9cc83fa9a9f2caa50fa539b4da9290e3c53c
                  MATRIX: 432->1|793->34|933->81|960->82|1038->134|1066->142|1094->143|1178->200|1207->208|1279->253|1296->261|1309->265|1357->275|1398->288|1456->319|1469->323|1495->328|1553->359|1573->370|1664->439|1784->528|1816->533
                  LINES: 17->1|22->2|27->3|28->4|31->7|31->7|31->7|36->12|36->12|39->15|39->15|39->15|39->15|40->16|41->17|41->17|41->17|42->18|42->18|42->18|45->21|46->22
                  -- GENERATED --
              */
          