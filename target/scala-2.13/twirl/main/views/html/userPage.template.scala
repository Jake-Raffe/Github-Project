
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
    <h1>"""),_display_(/*12.10*/user/*12.14*/.login),format.raw/*12.20*/("""</h1>
    <p>Username: """),_display_(/*13.19*/user/*13.23*/.login),format.raw/*13.29*/("""</p>
    <p>Date created: """),_display_(/*14.23*/user/*14.27*/.created_at),format.raw/*14.38*/("""</p>
    <p>Followers: """),_display_(/*15.20*/user/*15.24*/.followers),format.raw/*15.34*/("""</p>
    <p>Following: """),_display_(/*16.20*/user/*16.24*/.following),format.raw/*16.34*/("""</p>
<a href="""),_display_(/*17.10*/controllers/*17.21*/.routes.HomeController.getUserRepositories(user.login)),format.raw/*17.75*/(""">Repositories</a>

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
                  DATE: 2022-07-08T13:30:43.132077
                  SOURCE: /Users/jacob.raffe/Documents/Training/mock_github_play-project/app/views/userPage.scala.html
                  HASH: bcb01ae6ab53b638d1e634dd81e8882d96760978
                  MATRIX: 432->2|756->22|862->35|889->36|967->88|979->92|1005->98|1066->132|1079->136|1106->142|1157->166|1170->170|1197->176|1251->203|1264->207|1296->218|1347->242|1360->246|1391->256|1442->280|1455->284|1486->294|1527->308|1547->319|1622->373
                  LINES: 17->2|22->3|27->4|28->5|31->8|31->8|31->8|35->12|35->12|35->12|36->13|36->13|36->13|37->14|37->14|37->14|38->15|38->15|38->15|39->16|39->16|39->16|40->17|40->17|40->17
                  -- GENERATED --
              */
          