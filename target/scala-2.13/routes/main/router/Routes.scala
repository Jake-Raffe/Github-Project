// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/jacob.raffe/Documents/Training/mock_github_play-project/conf/routes
// @DATE:Thu Jul 07 16:43:16 BST 2022

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:2
  HomeController_2: controllers.HomeController,
  // @LINE:5
  Assets_1: controllers.Assets,
  // @LINE:7
  ApplicationController_0: controllers.ApplicationController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:2
    HomeController_2: controllers.HomeController,
    // @LINE:5
    Assets_1: controllers.Assets,
    // @LINE:7
    ApplicationController_0: controllers.ApplicationController
  ) = this(errorHandler, HomeController_2, Assets_1, ApplicationController_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_2, Assets_1, ApplicationController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github""", """controllers.ApplicationController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/""" + "$" + """username<[^/]+>""", """controllers.ApplicationController.read(username:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/user""", """controllers.ApplicationController.create()"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/""" + "$" + """username<[^/]+>""", """controllers.ApplicationController.update(username:String)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/""" + "$" + """username<[^/]+>""", """controllers.ApplicationController.delete(username:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/add/""" + "$" + """username<[^/]+>""", """controllers.ApplicationController.addUser(username:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/users/""" + "$" + """username<[^/]+>""", """controllers.HomeController.getUser(username:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/users/""" + "$" + """username<[^/]+>/repos""", """controllers.HomeController.getUserRepositories(username:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents""", """controllers.HomeController.getUserRepositoryContents(username:String, repoName:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents""" + "$" + """path<[^/]+>""", """controllers.HomeController.getUserRepositoryContentsPath(username:String, repoName:String, path:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:2
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_2.index(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:5
  private[this] lazy val controllers_Assets_versioned1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned1_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_ApplicationController_index2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github")))
  )
  private[this] lazy val controllers_ApplicationController_index2_invoker = createInvoker(
    ApplicationController_0.index(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "index",
      Nil,
      "GET",
      this.prefix + """github""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_ApplicationController_read3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/"), DynamicPart("username", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_read3_invoker = createInvoker(
    ApplicationController_0.read(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "read",
      Seq(classOf[String]),
      "GET",
      this.prefix + """github/""" + "$" + """username<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_ApplicationController_create4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/user")))
  )
  private[this] lazy val controllers_ApplicationController_create4_invoker = createInvoker(
    ApplicationController_0.create(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "create",
      Nil,
      "POST",
      this.prefix + """github/user""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_ApplicationController_update5_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/"), DynamicPart("username", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_update5_invoker = createInvoker(
    ApplicationController_0.update(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "update",
      Seq(classOf[String]),
      "PUT",
      this.prefix + """github/""" + "$" + """username<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_ApplicationController_delete6_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/"), DynamicPart("username", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_delete6_invoker = createInvoker(
    ApplicationController_0.delete(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "delete",
      Seq(classOf[String]),
      "DELETE",
      this.prefix + """github/""" + "$" + """username<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_ApplicationController_addUser7_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/add/"), DynamicPart("username", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_addUser7_invoker = createInvoker(
    ApplicationController_0.addUser(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "addUser",
      Seq(classOf[String]),
      "POST",
      this.prefix + """github/add/""" + "$" + """username<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_HomeController_getUser8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/users/"), DynamicPart("username", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_getUser8_invoker = createInvoker(
    HomeController_2.getUser(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getUser",
      Seq(classOf[String]),
      "GET",
      this.prefix + """github/users/""" + "$" + """username<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_HomeController_getUserRepositories9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/users/"), DynamicPart("username", """[^/]+""",true), StaticPart("/repos")))
  )
  private[this] lazy val controllers_HomeController_getUserRepositories9_invoker = createInvoker(
    HomeController_2.getUserRepositories(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getUserRepositories",
      Seq(classOf[String]),
      "GET",
      this.prefix + """github/users/""" + "$" + """username<[^/]+>/repos""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private[this] lazy val controllers_HomeController_getUserRepositoryContents10_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/repos/"), DynamicPart("username", """[^/]+""",true), StaticPart("/"), DynamicPart("repoName", """[^/]+""",true), StaticPart("/contents")))
  )
  private[this] lazy val controllers_HomeController_getUserRepositoryContents10_invoker = createInvoker(
    HomeController_2.getUserRepositoryContents(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getUserRepositoryContents",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_HomeController_getUserRepositoryContentsPath11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/repos/"), DynamicPart("username", """[^/]+""",true), StaticPart("/"), DynamicPart("repoName", """[^/]+""",true), StaticPart("/contents"), DynamicPart("path", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_getUserRepositoryContentsPath11_invoker = createInvoker(
    HomeController_2.getUserRepositoryContentsPath(fakeValue[String], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getUserRepositoryContentsPath",
      Seq(classOf[String], classOf[String], classOf[String]),
      "GET",
      this.prefix + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents""" + "$" + """path<[^/]+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:2
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_2.index())
      }
  
    // @LINE:5
    case controllers_Assets_versioned1_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned1_invoker.call(Assets_1.versioned(path, file))
      }
  
    // @LINE:7
    case controllers_ApplicationController_index2_route(params@_) =>
      call { 
        controllers_ApplicationController_index2_invoker.call(ApplicationController_0.index())
      }
  
    // @LINE:8
    case controllers_ApplicationController_read3_route(params@_) =>
      call(params.fromPath[String]("username", None)) { (username) =>
        controllers_ApplicationController_read3_invoker.call(ApplicationController_0.read(username))
      }
  
    // @LINE:9
    case controllers_ApplicationController_create4_route(params@_) =>
      call { 
        controllers_ApplicationController_create4_invoker.call(ApplicationController_0.create())
      }
  
    // @LINE:10
    case controllers_ApplicationController_update5_route(params@_) =>
      call(params.fromPath[String]("username", None)) { (username) =>
        controllers_ApplicationController_update5_invoker.call(ApplicationController_0.update(username))
      }
  
    // @LINE:11
    case controllers_ApplicationController_delete6_route(params@_) =>
      call(params.fromPath[String]("username", None)) { (username) =>
        controllers_ApplicationController_delete6_invoker.call(ApplicationController_0.delete(username))
      }
  
    // @LINE:13
    case controllers_ApplicationController_addUser7_route(params@_) =>
      call(params.fromPath[String]("username", None)) { (username) =>
        controllers_ApplicationController_addUser7_invoker.call(ApplicationController_0.addUser(username))
      }
  
    // @LINE:16
    case controllers_HomeController_getUser8_route(params@_) =>
      call(params.fromPath[String]("username", None)) { (username) =>
        controllers_HomeController_getUser8_invoker.call(HomeController_2.getUser(username))
      }
  
    // @LINE:17
    case controllers_HomeController_getUserRepositories9_route(params@_) =>
      call(params.fromPath[String]("username", None)) { (username) =>
        controllers_HomeController_getUserRepositories9_invoker.call(HomeController_2.getUserRepositories(username))
      }
  
    // @LINE:18
    case controllers_HomeController_getUserRepositoryContents10_route(params@_) =>
      call(params.fromPath[String]("username", None), params.fromPath[String]("repoName", None)) { (username, repoName) =>
        controllers_HomeController_getUserRepositoryContents10_invoker.call(HomeController_2.getUserRepositoryContents(username, repoName))
      }
  
    // @LINE:19
    case controllers_HomeController_getUserRepositoryContentsPath11_route(params@_) =>
      call(params.fromPath[String]("username", None), params.fromPath[String]("repoName", None), params.fromPath[String]("path", None)) { (username, repoName, path) =>
        controllers_HomeController_getUserRepositoryContentsPath11_invoker.call(HomeController_2.getUserRepositoryContentsPath(username, repoName, path))
      }
  }
}
