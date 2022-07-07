
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

object userPage extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[User,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*3.2*/(user: User):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
"""),format.raw/*5.1*/("""<!DOCTYPE html>
<html lang="en">
<head>
    <title>"""),_display_(/*8.13*/user/*8.17*/.login),format.raw/*8.23*/("""</title>

</head>
<body>

    <p>"""),_display_(/*13.9*/user/*13.13*/.login),format.raw/*13.19*/("""</p>
    <p>"""),_display_(/*14.9*/user/*14.13*/.created_at),format.raw/*14.24*/("""</p>
    <p>"""),_display_(/*15.9*/user/*15.13*/.followers),format.raw/*15.23*/("""</p>
    <p>"""),_display_(/*16.9*/user/*16.13*/.following),format.raw/*16.23*/("""</p>
<a href="""),_display_(/*17.10*/controllers/*17.21*/.routes.HomeController.getUserRepositories(user.login)),format.raw/*17.75*/(""">Repositories</a>
"""),format.raw/*20.7*/("""
"""),format.raw/*21.1*/("""</body>
</html>"""))
      }
    }
  }

  def render(user:User): play.twirl.api.HtmlFormat.Appendable = apply(user)

  def f:((User) => play.twirl.api.HtmlFormat.Appendable) = (user) => apply(user)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2022-07-07T11:24:50.650575
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/userPage.scala.html
                  HASH: b20d4781592f835fccb4bf2ea1e0a3f635fdf14a
                  MATRIX: 432->2|756->22|862->35|889->36|967->88|979->92|1005->98|1065->132|1078->136|1105->142|1144->155|1157->159|1189->170|1228->183|1241->187|1272->197|1311->210|1324->214|1355->224|1396->238|1416->249|1491->303|1536->378|1564->379
                  LINES: 17->2|22->3|27->4|28->5|31->8|31->8|31->8|36->13|36->13|36->13|37->14|37->14|37->14|38->15|38->15|38->15|39->16|39->16|39->16|40->17|40->17|40->17|41->20|42->21
                  -- GENERATED --
              */
          