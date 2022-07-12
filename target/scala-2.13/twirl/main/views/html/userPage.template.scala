
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
    <h3>Github</h3>
    <hr>
    <h1>"""),_display_(/*14.10*/user/*14.14*/.login),format.raw/*14.20*/("""</h1>
    <p>Username: """),_display_(/*15.19*/user/*15.23*/.login),format.raw/*15.29*/("""</p>
    <p>Date created: """),_display_(/*16.23*/user/*16.27*/.created_at),format.raw/*16.38*/("""</p>
    <p>Followers: """),_display_(/*17.20*/user/*17.24*/.followers),format.raw/*17.34*/("""</p>
    <p>Following: """),_display_(/*18.20*/user/*18.24*/.following),format.raw/*18.34*/("""</p>
    <a href="""),_display_(/*19.14*/controllers/*19.25*/.routes.HomeController.getUserRepositories(user.login)),format.raw/*19.79*/(""">View repositories</a>

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
                  DATE: 2022-07-12T15:37:52.231975
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/userPage.scala.html
                  HASH: 12aaee83a6fed82471e9419738014013d5e1cf73
                  MATRIX: 432->2|756->22|862->35|889->36|967->88|979->92|1005->98|1095->161|1108->165|1135->171|1186->195|1199->199|1226->205|1280->232|1293->236|1325->247|1376->271|1389->275|1420->285|1471->309|1484->313|1515->323|1560->341|1580->352|1655->406
                  LINES: 17->2|22->3|27->4|28->5|31->8|31->8|31->8|37->14|37->14|37->14|38->15|38->15|38->15|39->16|39->16|39->16|40->17|40->17|40->17|41->18|41->18|41->18|42->19|42->19|42->19
                  -- GENERATED --
              */
          