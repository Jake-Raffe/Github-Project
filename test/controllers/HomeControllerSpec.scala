package controllers

import baseSpec.BaseSpec
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.mvc.ControllerComponents
import play.api.test.Helpers._
import play.api.test._


class HomeControllerSpec extends BaseSpec with Injecting with GuiceOneAppPerSuite {

  val controllerComponents: ControllerComponents = Helpers.stubControllerComponents()

//  "HomeController .getUser" should {
//    "render the index page from a new instance of controller" in {
//      val controller = new HomeController(controllerComponents, githubService, executionContext)
//      val home = testHomeController.index().apply(FakeRequest(GET, "/"))
//
//      status(home) mustBe OK
//      contentType(home) mustBe Some("text/html")
//      contentAsString(home) must include("Welcome to Github")
//    }
//
//    "render the index page from the application" in {
//      val controller = inject[HomeController]
//      val home = controller.index().apply(FakeRequest(GET, "/"))
//
//      status(home) mustBe OK
//      contentType(home) mustBe Some("text/html")
//      contentAsString(home) must include("Welcome to Github")
//    }
//
//    "render the index page from the router" in {
//      val request = FakeRequest(GET, "/")
//      val home = route(app, request).get
//
//      status(home) mustBe OK
//      contentType(home) mustBe Some("text/html")
//      contentAsString(home) must include("Welcome to Github")
//    }

}
