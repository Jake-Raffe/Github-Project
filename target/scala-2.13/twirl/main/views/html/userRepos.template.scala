
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

object userRepos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Play Scala API demo")/*3.29*/ {_display_(Seq[Any](format.raw/*3.31*/("""

"""),format.raw/*5.1*/("""<div class="container h-100 d-flex justify-content-center">

    <div class="jumbotron my-auto">

        <div class="container mb-5">
            <h1 class="display-3 row">Welcome to GitHub</h1>
        </div>

      <p>This is the repo page</p>
    </div>

</div>
""")))}),format.raw/*17.2*/("""
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2022-07-05T11:32:57.765737
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/userRepos.scala.html
                  HASH: 471aa91260da2584b9aed49b4ddd4def7d9e1f09
                  MATRIX: 726->1|822->4|849->6|884->33|923->35|951->37|1248->304
                  LINES: 21->1|26->2|27->3|27->3|27->3|29->5|41->17
                  -- GENERATED --
              */
          