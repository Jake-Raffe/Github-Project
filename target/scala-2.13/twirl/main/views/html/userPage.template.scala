
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
    """),format.raw/*8.58*/("""
    """),format.raw/*9.5*/("""<title>"""),_display_(/*9.13*/user/*9.17*/.login),format.raw/*9.23*/("""</title>

</head>
<body>

    <p>"""),_display_(/*14.9*/user/*14.13*/.login),format.raw/*14.19*/("""</p>
    <p>"""),_display_(/*15.9*/user/*15.13*/.created_at),format.raw/*15.24*/("""</p>
    <p>"""),_display_(/*16.9*/user/*16.13*/.followers),format.raw/*16.23*/("""</p>
    <p>"""),_display_(/*17.9*/user/*17.13*/.following),format.raw/*17.23*/("""</p>

<a href="userRepos.scala.html">Repositories</a>
</body>
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
                  DATE: 2022-07-06T14:17:04.403822
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/userPage.scala.html
                  HASH: 4a215d48c9e96f3d545c788f5d7d70a25f05d6a4
                  MATRIX: 432->2|756->22|862->35|889->36|960->133|991->138|1025->146|1037->150|1063->156|1123->190|1136->194|1163->200|1202->213|1215->217|1247->228|1286->241|1299->245|1330->255|1369->268|1382->272|1413->282
                  LINES: 17->2|22->3|27->4|28->5|31->8|32->9|32->9|32->9|32->9|37->14|37->14|37->14|38->15|38->15|38->15|39->16|39->16|39->16|40->17|40->17|40->17
                  -- GENERATED --
              */
          