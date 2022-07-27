
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

object userRepoContentsPage extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[String,String,String,List[Content],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*3.2*/(username: String, repoName: String, path: String)(contents: List[Content]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""

"""),format.raw/*6.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
    <title>"""),_display_(/*9.13*/repoName),_display_(/*9.22*/path),format.raw/*9.26*/("""</title>

</head>
<body>

    <h3>Github</h3>
    <a href="""),_display_(/*15.14*/{controllers.routes.HomeController.getUser(username)}),format.raw/*15.67*/(""">Back to """),_display_(/*15.77*/username),format.raw/*15.85*/("""</a>
    <a href="""),_display_(/*16.14*/{controllers.routes.HomeController.getUserRepositories(username)}),format.raw/*16.79*/(""">Back to Repositories</a>
    <a href="""),_display_(/*17.14*/{controllers.routes.HomeController.getUserRepositoryContents(username, repoName, "repo-contents")}),format.raw/*17.112*/(""">Back to """),_display_(/*17.122*/repoName),format.raw/*17.130*/("""</a>
    <hr>
    <h1>"""),_display_(/*19.10*/repoName),_display_(/*19.19*/path),format.raw/*19.23*/(""" """),format.raw/*19.24*/("""contents:</h1>

    <section>
        <p>-----------------------</p>
        """),_display_(/*23.10*/contents/*23.18*/.map/*23.22*/ { file =>_display_(Seq[Any](format.raw/*23.32*/("""
            """),format.raw/*24.13*/("""<element>
                <h4>"""),_display_(/*25.22*/file/*25.26*/.name),format.raw/*25.31*/("""</h4>
                <p>"""),_display_(/*26.21*/{ if (file.`type`.contains("dir")) "Folder" else if(file.`type`.contains("file")) "File" else "Other"}),format.raw/*26.123*/("""</p>

                <a href="""),_display_(/*28.26*/{
                   if (file.`type`.contains("dir"))
                        controllers.routes.HomeController.getUserRepositoryContents(username, repoName, s"/${file.path}")
                   else if (file.`type`.contains("file"))
                        controllers.routes.HomeController.getFileContents(username, repoName, file.path)
                }),format.raw/*33.18*/(""">
                    """),_display_(/*34.22*/{ if (file.`type`.contains("dir")) "Open folder"
                        else if(file.`type`.contains("file")) "Open file"
                        else "Open"}),format.raw/*36.37*/("""
                """),format.raw/*37.17*/("""</a>
            </element>
            <p>-----------------------</p>
        """)))}),format.raw/*40.10*/("""
    """),format.raw/*41.5*/("""</section>

    <a href="""),_display_(/*43.14*/{controllers.routes.HomeController.openNewFilePage(username, repoName, {if (path.equals("")) "top" else path }  )   }),format.raw/*43.131*/(""">Create new file</a>

</body>
</html>
"""))
      }
    }
  }

  def render(username:String,repoName:String,path:String,contents:List[Content]): play.twirl.api.HtmlFormat.Appendable = apply(username,repoName,path)(contents)

  def f:((String,String,String) => (List[Content]) => play.twirl.api.HtmlFormat.Appendable) = (username,repoName,path) => (contents) => apply(username,repoName,path)(contents)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2022-07-27T14:07:48.319474
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/userRepoContentsPage.scala.html
                  HASH: 5b27037361c3fd8f40a344c5e91f0de4c68df64a
                  MATRIX: 432->1|468->31|831->48|1000->124|1028->126|1106->178|1134->187|1158->191|1244->250|1318->303|1355->313|1384->321|1429->339|1515->404|1581->443|1701->541|1739->551|1769->559|1819->582|1848->591|1873->595|1902->596|2007->674|2024->682|2037->686|2085->696|2126->709|2184->740|2197->744|2223->749|2276->775|2400->877|2458->908|2835->1264|2885->1287|3065->1446|3110->1463|3221->1543|3253->1548|3305->1573|3444->1690
                  LINES: 17->1|18->2|23->3|28->4|30->6|33->9|33->9|33->9|39->15|39->15|39->15|39->15|40->16|40->16|41->17|41->17|41->17|41->17|43->19|43->19|43->19|43->19|47->23|47->23|47->23|47->23|48->24|49->25|49->25|49->25|50->26|50->26|52->28|57->33|58->34|60->36|61->37|64->40|65->41|67->43|67->43
                  -- GENERATED --
              */
          