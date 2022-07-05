
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

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /*
* This template is called from the `index` template. This template
* handles the rendering of the page header and body tags. It takes
* two arguments, a `String` for the title of the page and an `Html`
* object to insert into the body of the page.
*/
  def apply/*7.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.1*/("""
"""),format.raw/*9.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
    """),format.raw/*12.58*/("""
    """),format.raw/*13.5*/("""<title>"""),_display_(/*13.13*/title),format.raw/*13.18*/("""</title>
    <link rel="stylesheet" media="screen" href=""""),_display_(/*14.50*/routes/*14.56*/.Assets.versioned("stylesheets/main.css")),format.raw/*14.97*/("""">
    <link rel="shortcut icon" type="image/png" href=""""),_display_(/*15.55*/routes/*15.61*/.Assets.versioned("images/favicon.png")),format.raw/*15.100*/("""">

</head>
<body>
"""),format.raw/*20.23*/("""
"""),_display_(/*21.2*/content),format.raw/*21.9*/("""

"""),format.raw/*23.1*/("""<script src=""""),_display_(/*23.15*/routes/*23.21*/.Assets.versioned("javascripts/main.js")),format.raw/*23.61*/("""" type="text/javascript"></script>
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
                  DATE: 2022-07-05T11:32:57.739558
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/main.scala.html
                  HASH: 67de0d47eb288c74215d1d8e4debbe389a477baa
                  MATRIX: 982->255|1106->286|1133->287|1205->384|1237->389|1272->397|1298->402|1383->460|1398->466|1460->507|1544->564|1559->570|1620->609|1667->709|1695->711|1722->718|1751->720|1792->734|1807->740|1868->780
                  LINES: 26->7|31->8|32->9|35->12|36->13|36->13|36->13|37->14|37->14|37->14|38->15|38->15|38->15|42->20|43->21|43->21|45->23|45->23|45->23|45->23
                  -- GENERATED --
              */
          