
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

object userRepoContentsPathPage extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[String,String,String,List[Content],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(username: String)(repoName: String)(path: String)(contents: List[Content]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),format.raw/*4.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
    <title>"""),_display_(/*7.13*/username),format.raw/*7.21*/("""'s """),_display_(/*7.25*/repoName),_display_(/*7.34*/path),format.raw/*7.38*/("""</title>

</head>
<body>

    <h1>"""),_display_(/*12.10*/repoName),_display_(/*12.19*/path),format.raw/*12.23*/(""" """),format.raw/*12.24*/("""contents:</h1>

    <section>
        """),_display_(/*15.10*/contents/*15.18*/.map/*15.22*/ { file =>_display_(Seq[Any](format.raw/*15.32*/("""
            """),format.raw/*16.13*/("""<element>
                <h4>"""),_display_(/*17.22*/file/*17.26*/.name),format.raw/*17.31*/("""</h4>
                <p>"""),_display_(/*18.21*/{ if (file.`type`.contains("dir")) "Folder" else if(file.`type`.contains("file")) "File" else "Other"}),format.raw/*18.123*/("""</p>
                """),format.raw/*21.19*/("""

                """),format.raw/*23.17*/("""<a href="""),_display_(/*23.26*/controllers/*23.37*/.routes.HomeController.getUserRepositoryContentsPath(username, repoName, s"$path/${file.name}")),format.raw/*23.132*/(""">Open</a>
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

  def render(username:String,repoName:String,path:String,contents:List[Content]): play.twirl.api.HtmlFormat.Appendable = apply(username)(repoName)(path)(contents)

  def f:((String) => (String) => (String) => (List[Content]) => play.twirl.api.HtmlFormat.Appendable) = (username) => (repoName) => (path) => (contents) => apply(username)(repoName)(path)(contents)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2022-07-08T11:45:24.557883
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/userRepoContentsPathPage.scala.html
                  HASH: 6fb66a0e38d06473a633d974c7fb134d351141b5
                  MATRIX: 432->1|812->31|981->107|1008->108|1086->160|1114->168|1144->172|1172->181|1196->185|1258->220|1287->229|1312->233|1341->234|1407->273|1424->281|1437->285|1485->295|1526->308|1584->339|1597->343|1623->348|1676->374|1800->476|1849->552|1895->570|1931->579|1951->590|2068->685|2184->770|2216->775
                  LINES: 17->1|22->2|27->3|28->4|31->7|31->7|31->7|31->7|31->7|36->12|36->12|36->12|36->12|39->15|39->15|39->15|39->15|40->16|41->17|41->17|41->17|42->18|42->18|43->21|45->23|45->23|45->23|45->23|48->26|49->27
                  -- GENERATED --
              */
          