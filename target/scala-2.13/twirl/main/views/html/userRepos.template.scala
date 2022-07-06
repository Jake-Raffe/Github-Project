
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
/*1.2*/import models.User

object userRepos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[User,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(user: User):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("Play Scala API demo")/*4.29*/ {_display_(Seq[Any](format.raw/*4.31*/("""

"""),format.raw/*6.1*/("""<div class="container h-100 d-flex justify-content-center">

    <div class="jumbotron my-auto">

        <div class="container mb-5">
            <h1 class="display-3 row">Welcome to GitHub</h1>
        </div>

      <p>This is the repo page</p>
    </div>

</div>
""")))}),format.raw/*18.2*/("""
"""))
      }
    }
  }

  def render(user:User): play.twirl.api.HtmlFormat.Appendable = apply(user)

  def f:((User) => play.twirl.api.HtmlFormat.Appendable) = (user) => apply(user)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2022-07-06T14:17:04.430750
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/userRepos.scala.html
                  HASH: cade6539f931d9834be2fe61a6fb3b12722e0418
                  MATRIX: 432->1|757->21|863->34|890->36|925->63|964->65|992->67|1289->334
                  LINES: 17->1|22->2|27->3|28->4|28->4|28->4|30->6|42->18
                  -- GENERATED --
              */
          