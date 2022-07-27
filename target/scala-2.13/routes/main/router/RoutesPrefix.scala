// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/jacob.raffe/Documents/Training/mock_github_play-project/conf/routes
// @DATE:Wed Jul 27 14:12:49 BST 2022


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
