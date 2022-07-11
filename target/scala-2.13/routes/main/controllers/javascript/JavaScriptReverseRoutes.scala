// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/jacob.raffe/Documents/Training/mock_github_play-project/conf/routes
// @DATE:Mon Jul 11 09:11:29 BST 2022

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:2
package controllers.javascript {

  // @LINE:7
  class ReverseApplicationController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def read: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ApplicationController.read",
      """
        function(username0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "github/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("username", username0))})
        }
      """
    )
  
    // @LINE:9
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ApplicationController.create",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "github/user"})
        }
      """
    )
  
    // @LINE:11
    def delete: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ApplicationController.delete",
      """
        function(username0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "github/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("username", username0))})
        }
      """
    )
  
    // @LINE:10
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ApplicationController.update",
      """
        function(username0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "github/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("username", username0))})
        }
      """
    )
  
    // @LINE:13
    def addUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ApplicationController.addUser",
      """
        function(username0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "github/add/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("username", username0))})
        }
      """
    )
  
    // @LINE:7
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ApplicationController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "github"})
        }
      """
    )
  
  }

  // @LINE:2
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:20
    def getFileContents: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getFileContents",
      """
        function(username0,repoName1,path2) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "github/repos/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("username", username0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("repoName", repoName1)) + "/contents" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("path", path2)) + "/open"})
        }
      """
    )
  
    // @LINE:16
    def getUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getUser",
      """
        function(username0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "github/users/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("username", username0))})
        }
      """
    )
  
    // @LINE:18
    def getUserRepositoryContents: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getUserRepositoryContents",
      """
        function(username0,repoName1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "github/repos/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("username", username0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("repoName", repoName1)) + "/contents"})
        }
      """
    )
  
    // @LINE:17
    def getUserRepositories: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getUserRepositories",
      """
        function(username0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "github/users/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("username", username0)) + "/repos"})
        }
      """
    )
  
    // @LINE:19
    def getUserRepositoryContentsPath: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getUserRepositoryContentsPath",
      """
        function(username0,repoName1,path2) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "github/repos/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("username", username0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("repoName", repoName1)) + "/contents" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("path", path2))})
        }
      """
    )
  
    // @LINE:2
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:5
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
