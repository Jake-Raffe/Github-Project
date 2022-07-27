// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/jacob.raffe/Documents/Training/mock_github_play-project/conf/routes
// @DATE:Wed Jul 27 14:12:49 BST 2022

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
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/users/""" + "$" + """username<[^/]+>""", """controllers.HomeController.getUser(username:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/users/""" + "$" + """username<[^/]+>/repos""", """controllers.HomeController.getUserRepositories(username:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents/""" + "$" + """path<[^/]+>/open""", """controllers.HomeController.getUserRepositoryContents(username:String, repoName:String, path:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents/""" + "$" + """path<[^/]+>/openFile""", """controllers.HomeController.getFileContents(username:String, repoName:String, path:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents/""" + "$" + """path<[^/]+>/new""", """controllers.HomeController.openNewFilePage(username:String, repoName:String, path:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents/""" + "$" + """path<[^/]+>/update""", """controllers.HomeController.openUpdateFilePage(username:String, repoName:String, path:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents/""" + "$" + """path<[^/]+>/create""", """controllers.HomeController.createNewFile(username:String, repoName:String, path:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents/""" + "$" + """path<[^/]+>/""" + "$" + """sha<[^/]+>/update""", """controllers.HomeController.updateFile(username:String, repoName:String, path:String, sha:String)"""),
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

  // @LINE:14
  private[this] lazy val controllers_HomeController_getUser7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/users/"), DynamicPart("username", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_getUser7_invoker = createInvoker(
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

  // @LINE:15
  private[this] lazy val controllers_HomeController_getUserRepositories8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/users/"), DynamicPart("username", """[^/]+""",true), StaticPart("/repos")))
  )
  private[this] lazy val controllers_HomeController_getUserRepositories8_invoker = createInvoker(
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

  // @LINE:16
  private[this] lazy val controllers_HomeController_getUserRepositoryContents9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/repos/"), DynamicPart("username", """[^/]+""",true), StaticPart("/"), DynamicPart("repoName", """[^/]+""",true), StaticPart("/contents/"), DynamicPart("path", """[^/]+""",true), StaticPart("/open")))
  )
  private[this] lazy val controllers_HomeController_getUserRepositoryContents9_invoker = createInvoker(
    HomeController_2.getUserRepositoryContents(fakeValue[String], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getUserRepositoryContents",
      Seq(classOf[String], classOf[String], classOf[String]),
      "GET",
      this.prefix + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents/""" + "$" + """path<[^/]+>/open""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_HomeController_getFileContents10_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/repos/"), DynamicPart("username", """[^/]+""",true), StaticPart("/"), DynamicPart("repoName", """[^/]+""",true), StaticPart("/contents/"), DynamicPart("path", """[^/]+""",true), StaticPart("/openFile")))
  )
  private[this] lazy val controllers_HomeController_getFileContents10_invoker = createInvoker(
    HomeController_2.getFileContents(fakeValue[String], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getFileContents",
      Seq(classOf[String], classOf[String], classOf[String]),
      "GET",
      this.prefix + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents/""" + "$" + """path<[^/]+>/openFile""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_HomeController_openNewFilePage11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/repos/"), DynamicPart("username", """[^/]+""",true), StaticPart("/"), DynamicPart("repoName", """[^/]+""",true), StaticPart("/contents/"), DynamicPart("path", """[^/]+""",true), StaticPart("/new")))
  )
  private[this] lazy val controllers_HomeController_openNewFilePage11_invoker = createInvoker(
    HomeController_2.openNewFilePage(fakeValue[String], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "openNewFilePage",
      Seq(classOf[String], classOf[String], classOf[String]),
      "GET",
      this.prefix + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents/""" + "$" + """path<[^/]+>/new""",
      """""",
      Seq()
    )
  )

  // @LINE:20
  private[this] lazy val controllers_HomeController_openUpdateFilePage12_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/repos/"), DynamicPart("username", """[^/]+""",true), StaticPart("/"), DynamicPart("repoName", """[^/]+""",true), StaticPart("/contents/"), DynamicPart("path", """[^/]+""",true), StaticPart("/update")))
  )
  private[this] lazy val controllers_HomeController_openUpdateFilePage12_invoker = createInvoker(
    HomeController_2.openUpdateFilePage(fakeValue[String], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "openUpdateFilePage",
      Seq(classOf[String], classOf[String], classOf[String]),
      "GET",
      this.prefix + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents/""" + "$" + """path<[^/]+>/update""",
      """""",
      Seq()
    )
  )

  // @LINE:22
  private[this] lazy val controllers_HomeController_createNewFile13_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/repos/"), DynamicPart("username", """[^/]+""",true), StaticPart("/"), DynamicPart("repoName", """[^/]+""",true), StaticPart("/contents/"), DynamicPart("path", """[^/]+""",true), StaticPart("/create")))
  )
  private[this] lazy val controllers_HomeController_createNewFile13_invoker = createInvoker(
    HomeController_2.createNewFile(fakeValue[String], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "createNewFile",
      Seq(classOf[String], classOf[String], classOf[String]),
      "GET",
      this.prefix + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents/""" + "$" + """path<[^/]+>/create""",
      """""",
      Seq()
    )
  )

  // @LINE:23
  private[this] lazy val controllers_HomeController_updateFile14_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("github/repos/"), DynamicPart("username", """[^/]+""",true), StaticPart("/"), DynamicPart("repoName", """[^/]+""",true), StaticPart("/contents/"), DynamicPart("path", """[^/]+""",true), StaticPart("/"), DynamicPart("sha", """[^/]+""",true), StaticPart("/update")))
  )
  private[this] lazy val controllers_HomeController_updateFile14_invoker = createInvoker(
    HomeController_2.updateFile(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "updateFile",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      this.prefix + """github/repos/""" + "$" + """username<[^/]+>/""" + "$" + """repoName<[^/]+>/contents/""" + "$" + """path<[^/]+>/""" + "$" + """sha<[^/]+>/update""",
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
  
    // @LINE:14
    case controllers_HomeController_getUser7_route(params@_) =>
      call(params.fromPath[String]("username", None)) { (username) =>
        controllers_HomeController_getUser7_invoker.call(HomeController_2.getUser(username))
      }
  
    // @LINE:15
    case controllers_HomeController_getUserRepositories8_route(params@_) =>
      call(params.fromPath[String]("username", None)) { (username) =>
        controllers_HomeController_getUserRepositories8_invoker.call(HomeController_2.getUserRepositories(username))
      }
  
    // @LINE:16
    case controllers_HomeController_getUserRepositoryContents9_route(params@_) =>
      call(params.fromPath[String]("username", None), params.fromPath[String]("repoName", None), params.fromPath[String]("path", None)) { (username, repoName, path) =>
        controllers_HomeController_getUserRepositoryContents9_invoker.call(HomeController_2.getUserRepositoryContents(username, repoName, path))
      }
  
    // @LINE:17
    case controllers_HomeController_getFileContents10_route(params@_) =>
      call(params.fromPath[String]("username", None), params.fromPath[String]("repoName", None), params.fromPath[String]("path", None)) { (username, repoName, path) =>
        controllers_HomeController_getFileContents10_invoker.call(HomeController_2.getFileContents(username, repoName, path))
      }
  
    // @LINE:19
    case controllers_HomeController_openNewFilePage11_route(params@_) =>
      call(params.fromPath[String]("username", None), params.fromPath[String]("repoName", None), params.fromPath[String]("path", None)) { (username, repoName, path) =>
        controllers_HomeController_openNewFilePage11_invoker.call(HomeController_2.openNewFilePage(username, repoName, path))
      }
  
    // @LINE:20
    case controllers_HomeController_openUpdateFilePage12_route(params@_) =>
      call(params.fromPath[String]("username", None), params.fromPath[String]("repoName", None), params.fromPath[String]("path", None)) { (username, repoName, path) =>
        controllers_HomeController_openUpdateFilePage12_invoker.call(HomeController_2.openUpdateFilePage(username, repoName, path))
      }
  
    // @LINE:22
    case controllers_HomeController_createNewFile13_route(params@_) =>
      call(params.fromPath[String]("username", None), params.fromPath[String]("repoName", None), params.fromPath[String]("path", None)) { (username, repoName, path) =>
        controllers_HomeController_createNewFile13_invoker.call(HomeController_2.createNewFile(username, repoName, path))
      }
  
    // @LINE:23
    case controllers_HomeController_updateFile14_route(params@_) =>
      call(params.fromPath[String]("username", None), params.fromPath[String]("repoName", None), params.fromPath[String]("path", None), params.fromPath[String]("sha", None)) { (username, repoName, path, sha) =>
        controllers_HomeController_updateFile14_invoker.call(HomeController_2.updateFile(username, repoName, path, sha))
      }
  }
}
