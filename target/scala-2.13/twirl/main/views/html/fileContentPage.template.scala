
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

object fileContentPage extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[String,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(username: String)(repoName: String)(path: String)(fileContent: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),format.raw/*4.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
    <title>"""),_display_(/*7.13*/repoName),_display_(/*7.22*/path),format.raw/*7.26*/("""</title>

</head>
<body>

    <h1>"""),_display_(/*12.10*/repoName),_display_(/*12.19*/path),format.raw/*12.23*/(""" """),format.raw/*12.24*/("""content:</h1>

    <section>
        <p>-----------------------</p>
            """),_display_(/*16.14*/fileContent),format.raw/*16.25*/("""
        """),format.raw/*17.9*/("""<p>-----------------------</p>
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
                  DATE: 2022-07-08T13:30:43.107614
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/fileContentPage.scala.html
                  HASH: 79963e87a547d8c623e1dc6b17070b0ce85e31ae
                  MATRIX: 432->1|812->47|977->119|1004->120|1082->172|1110->181|1134->185|1196->220|1225->229|1250->233|1279->234|1387->315|1419->326|1455->335
                  LINES: 17->1|22->2|27->3|28->4|31->7|31->7|31->7|36->12|36->12|36->12|36->12|40->16|40->16|41->17
                  -- GENERATED --
              */
          