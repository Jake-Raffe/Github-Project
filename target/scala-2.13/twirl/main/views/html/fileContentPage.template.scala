
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
    <title>"""),_display_(/*9.13*/repoName),format.raw/*9.21*/("""/"""),_display_(/*9.23*/path),format.raw/*9.27*/("""</title>

</head>
<body>
    <h3>Github</h3>
    <a href="""),_display_(/*14.14*/{controllers.routes.HomeController.getUser(username)}),format.raw/*14.67*/(""">Back to """),_display_(/*14.77*/username),format.raw/*14.85*/("""</a>
    <a href="""),_display_(/*15.14*/{controllers.routes.HomeController.getUserRepositories(username)}),format.raw/*15.79*/(""">Back to Repositories</a>
    <a href="""),_display_(/*16.14*/{controllers.routes.HomeController.getUserRepositoryContents(username, repoName, "repo-contents")}),format.raw/*16.112*/(""">Back to """),_display_(/*16.122*/repoName),format.raw/*16.130*/("""</a>
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
                  DATE: 2022-07-27T15:50:08.316600
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/fileContentPage.scala.html
                  HASH: d0f87d52326d49ddb5c56cbab34c5fdac1881b78
                  MATRIX: 432->1|484->47|515->72|891->114|1056->186|1083->187|1161->239|1189->247|1217->249|1241->253|1326->311|1400->364|1437->374|1466->382|1511->400|1597->465|1663->504|1783->602|1821->612|1851->620|1901->643|1930->651|1959->653|1984->657|2013->658|2123->741|2161->758|2263->833|2364->913
                  LINES: 17->1|18->2|19->3|24->4|29->5|30->6|33->9|33->9|33->9|33->9|38->14|38->14|38->14|38->14|39->15|39->15|40->16|40->16|40->16|40->16|42->18|42->18|42->18|42->18|42->18|46->22|46->22|50->26|50->26
                  -- GENERATED --
              */
          