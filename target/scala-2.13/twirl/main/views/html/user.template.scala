
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

object user extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*3.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
"""),format.raw/*5.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
    """),format.raw/*8.58*/("""
    """),format.raw/*9.5*/("""<title>"""),_display_(/*9.13*/title),format.raw/*9.18*/("""</title>
    <link rel="stylesheet" media="screen" href=""""),_display_(/*10.50*/routes/*10.56*/.Assets.versioned("stylesheets/main.css")),format.raw/*10.97*/("""">
    <link rel="shortcut icon" type="image/png" href=""""),_display_(/*11.55*/routes/*11.61*/.Assets.versioned("images/favicon.png")),format.raw/*11.100*/("""">

</head>
<body>
"""),format.raw/*16.23*/("""
"""),_display_(/*17.2*/content),format.raw/*17.9*/("""

"""),format.raw/*19.1*/("""<script src=""""),_display_(/*19.15*/routes/*19.21*/.Assets.versioned("javascripts/main.js")),format.raw/*19.61*/("""" type="text/javascript"></script>

<a href="userRepos.scala.html">Repositories</a>
</body>
</html>"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2022-07-05T11:32:57.717954
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/user.scala.html
                  HASH: cbeddd9f262969117e22191af5ff348cb870a796
                  MATRIX: 733->3|857->34|884->35|955->132|986->137|1020->145|1045->150|1130->208|1145->214|1207->255|1291->312|1306->318|1367->357|1414->457|1442->459|1469->466|1498->468|1539->482|1554->488|1615->528
                  LINES: 21->3|26->4|27->5|30->8|31->9|31->9|31->9|32->10|32->10|32->10|33->11|33->11|33->11|37->16|38->17|38->17|40->19|40->19|40->19|40->19
                  -- GENERATED --
              */
          