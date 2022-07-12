
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
  def apply/*4.2*/(username: String)(repoName: String)(path: String)(fileContent: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""
"""),format.raw/*6.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
    <title>"""),_display_(/*9.13*/repoName),_display_(/*9.22*/path),format.raw/*9.26*/("""</title>

</head>
<body>

    <h1>"""),_display_(/*14.10*/repoName),_display_(/*14.19*/path),format.raw/*14.23*/(""" """),format.raw/*14.24*/("""content:</h1>

    <section>
        <p>-----------------------</p>
            """),_display_(/*18.14*/fileContent),format.raw/*18.25*/("""
        """),format.raw/*19.9*/("""<p>-----------------------</p>
    </section>


</body>
</html>
"""))
      }
    }
  }

  def render(username:String,repoName:String,path:String,fileContent:String): play.twirl.api.HtmlFormat.Appendable = apply(username)(repoName)(path)(fileContent)

  def f:((String) => (String) => (String) => (String) => play.twirl.api.HtmlFormat.Appendable) = (username) => (repoName) => (path) => (fileContent) => apply(username)(repoName)(path)(fileContent)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2022-07-11T15:16:58.532192
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/fileContentPage.scala.html
                  HASH: d0759a66536adf1673656ac997c27f7a40e478e5
                  MATRIX: 432->1|484->47|515->72|891->114|1056->186|1083->187|1161->239|1189->248|1213->252|1275->287|1304->296|1329->300|1358->301|1466->382|1498->393|1534->402
                  LINES: 17->1|18->2|19->3|24->4|29->5|30->6|33->9|33->9|33->9|38->14|38->14|38->14|38->14|42->18|42->18|43->19
                  -- GENERATED --
              */
          