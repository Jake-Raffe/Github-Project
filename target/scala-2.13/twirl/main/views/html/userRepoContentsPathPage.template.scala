
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
    <title>"""),_display_(/*7.13*/repoName),_display_(/*7.22*/path),format.raw/*7.26*/("""</title>

</head>
<body>

    <h3>Github</h3>
    <a href="""),_display_(/*13.14*/{controllers.routes.HomeController.getUser(username)}),format.raw/*13.67*/(""">Back to """),_display_(/*13.77*/username),format.raw/*13.85*/("""</a>
    <a href="""),_display_(/*14.14*/{controllers.routes.HomeController.getUserRepositories(username)}),format.raw/*14.79*/(""">Back to Repositories</a>
    <a href="""),_display_(/*15.14*/{controllers.routes.HomeController.getUserRepositoryContents(username, repoName)}),format.raw/*15.95*/(""">Back to """),_display_(/*15.105*/repoName),format.raw/*15.113*/("""</a>
    <hr>
    <h1>"""),_display_(/*17.10*/repoName),_display_(/*17.19*/path),format.raw/*17.23*/(""" """),format.raw/*17.24*/("""contents:</h1>

    <section>
        <p>-----------------------</p>
        """),_display_(/*21.10*/contents/*21.18*/.map/*21.22*/ { file =>_display_(Seq[Any](format.raw/*21.32*/("""
            """),format.raw/*22.13*/("""<element>
                <h4>"""),_display_(/*23.22*/file/*23.26*/.name),format.raw/*23.31*/("""</h4>
                <p>"""),_display_(/*24.21*/{ if (file.`type`.contains("dir")) "Type: Folder" else if(file.`type`.contains("file")) "Type: File" else "Type: Other"}),format.raw/*24.141*/("""</p>
                """),format.raw/*27.19*/("""

                """),format.raw/*29.17*/("""<a href="""),_display_(/*29.26*/{
                   if (file.`type`.contains("dir"))
                        controllers.routes.HomeController.getUserRepositoryContentsPath(username, repoName, s"$path/${file.name}")
                    else if (file.`type`.contains("file"))
                        controllers.routes.HomeController.getFileContents(username, repoName, s"$path/${file.name}")
                }),format.raw/*34.18*/(""">
                    """),_display_(/*35.22*/{ if
                        (file.`type`.contains("dir")) "Open folder"
                    else if(file.`type`.contains("file")) "Open file"
                    else "Open"}),format.raw/*38.33*/("""
                """),format.raw/*39.17*/("""</a>

            </element>
            <p>-----------------------</p>
        """)))}),format.raw/*43.10*/("""
    """),format.raw/*44.5*/("""</section>

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
                  DATE: 2022-07-15T10:49:52.367464
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/userRepoContentsPathPage.scala.html
                  HASH: e49c1ab72cc91955ee06916c89a4ea9e22fad3fd
                  MATRIX: 432->1|812->31|981->107|1008->108|1086->160|1114->169|1138->173|1224->232|1298->285|1335->295|1364->303|1409->321|1495->386|1561->425|1663->506|1701->516|1731->524|1781->547|1810->556|1835->560|1864->561|1969->639|1986->647|1999->651|2047->661|2088->674|2146->705|2159->709|2185->714|2238->740|2380->860|2429->936|2475->954|2511->963|2910->1341|2960->1364|3156->1539|3201->1556|3313->1637|3345->1642
                  LINES: 17->1|22->2|27->3|28->4|31->7|31->7|31->7|37->13|37->13|37->13|37->13|38->14|38->14|39->15|39->15|39->15|39->15|41->17|41->17|41->17|41->17|45->21|45->21|45->21|45->21|46->22|47->23|47->23|47->23|48->24|48->24|49->27|51->29|51->29|56->34|57->35|60->38|61->39|65->43|66->44
                  -- GENERATED --
              */
          