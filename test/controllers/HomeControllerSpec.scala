package controllers

import baseSpec.{BaseSpec, BaseSpecWithApplication}
import models.{CreatedFile, FileForm}
import org.scalamock.matchers.Matchers
import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AnyContentAsEmpty, ControllerComponents, Result}
import play.api.test.Helpers._
import play.api.test._

import scala.concurrent.Future


class HomeControllerSpec extends BaseSpecWithApplication with MockFactory with ScalaFutures with Matchers {

  val controllerComponents: ControllerComponents = Helpers.stubControllerComponents()
  val createFileForm = FileForm("NewFile.md", "FileContent")
  val createdFileObject = CreatedFile("Commit message", "contents", "main")
  val homeController = new HomeController(component, githubService, executionContext)

  "HomeController .creteNewFile" should {
    "create a file" in {
      val request: FakeRequest[AnyContentAsEmpty.type] = buildGet("/github/repos/jake-raffe/Github-Project/contents/create?fileName=FileName.md&fileContent=This+is+the+file+content")
      val createdResult = homeController.createNewFile("jake-raffe", "Github-Project", "")(request)

      status(createdResult)(defaultAwaitTimeout) shouldBe Status.SEE_OTHER
//      contentAsJson(createdResult)(defaultAwaitTimeout).as[JsValue] shouldBe Json.toJson(mockDataModel)
    }

  }

  "HomeController .filterPath" should {
    "replace '/src/blue' with 'src/blue'" in {
      HomeController.filterPath("/src/blue") shouldEqual "src/blue"
    }

  }

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
