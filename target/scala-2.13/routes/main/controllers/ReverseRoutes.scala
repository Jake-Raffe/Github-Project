// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/jacob.raffe/Documents/Training/mock_github_play-project/conf/routes
// @DATE:Fri Jul 15 10:49:51 BST 2022

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:2
package controllers {

  // @LINE:7
  class ReverseApplicationController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def read(username:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "github/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("username", username)))
    }
  
    // @LINE:9
    def create(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "github/user")
    }
  
    // @LINE:11
    def delete(username:String): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "github/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("username", username)))
    }
  
    // @LINE:10
    def update(username:String): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "github/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("username", username)))
    }
  
    // @LINE:7
    def index(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "github")
    }
  
  }

  // @LINE:2
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def getFileContents(username:String, repoName:String, path:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "github/repos/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("username", username)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("repoName", repoName)) + "/contents" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("path", path)) + "/open")
    }
  
    // @LINE:14
    def getUser(username:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "github/users/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("username", username)))
    }
  
    // @LINE:16
    def getUserRepositoryContents(username:String, repoName:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "github/repos/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("username", username)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("repoName", repoName)) + "/contents")
    }
  
    // @LINE:15
    def getUserRepositories(username:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "github/users/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("username", username)) + "/repos")
    }
  
    // @LINE:17
    def getUserRepositoryContentsPath(username:String, repoName:String, path:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "github/repos/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("username", username)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("repoName", repoName)) + "/contents" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("path", path)))
    }
  
    // @LINE:2
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:5
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
