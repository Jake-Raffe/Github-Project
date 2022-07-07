
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
/*2.2*/import models.User

object notFound extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*3.2*/(subject: String)(error: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
"""),format.raw/*5.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
    <title>Not Found</title>

</head>
<body>

    <p>Unable to find """),_display_(/*13.24*/subject),format.raw/*13.31*/(""", i.e. something went wrong...</p>
    <p>Fix it then refresh the page, or go back. Cheers</p>
    <hr>
    <br>
    <p>"""),_display_(/*17.9*/error),format.raw/*17.14*/("""</p>

</body>
</html>"""))
      }
    }
  }

  def render(subject:String,error:String): play.twirl.api.HtmlFormat.Appendable = apply(subject)(error)

  def f:((String) => (String) => play.twirl.api.HtmlFormat.Appendable) = (subject) => (error) => apply(subject)(error)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2022-07-07T15:24:20.508428
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/notFound.scala.html
                  HASH: ad836160a1f078943bc31eb65fcdc9ff7fef8b03
                  MATRIX: 432->2|765->22|891->55|918->56|1054->165|1082->172|1229->293|1255->298
                  LINES: 17->2|22->3|27->4|28->5|36->13|36->13|40->17|40->17
                  -- GENERATED --
              */
          