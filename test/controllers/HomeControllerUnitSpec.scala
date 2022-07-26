package controllers

import baseSpec.{BaseSpec, BaseSpecWithApplication}
import models.{APIError, Content, Repository, User}
import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.http.Status
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AnyContentAsEmpty, ControllerComponents, Request, Result}
import play.api.test.Helpers._
import play.api.test._
import services.GithubService

import scala.concurrent.{ExecutionContext, Future}


class HomeControllerUnitSpec extends BaseSpecWithApplication with MockFactory {

  val mockGithubService: GithubService = mock[GithubService]

  val testHomeController = new HomeController(
    component,
    mockGithubService,
    executionContext
  )

//  val jakeUser = new User(
//    "Jake-Raffe", "2022-01-07T19:56:27Z", None, 4,5)
//  val jakeRepos = List(
//    new Repository("Back-end_Project"), new Repository("Flight-Booking-CLI-Project"))
//  val repoContents = List(
//    new Content(".idea", "dir"), new Content("README.md", "file"))
//  val directoryContents = List(
//    new Content("main", "dir"), new Content("test", "dir"))
//  val fileContentString = """# Default ignored files\n/shelf/\n/workspace.xml"""
//
//  "HomeController .getUser" should {
//
//    "get a user by their username and render their details on a webpage" in {
//      val request: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/users/jake-raffe")
//      (mockGithubService.getUser(_: String)(_: ExecutionContext))
//        .expects("jake-raffe", executionContext)
//        .returning(Future(Right(jakeUser)))
//        .once()
//      val createdResult: Future[Result] = testHomeController.getUser("jake-raffe")(request)
//
//      status(createdResult) shouldBe Status.OK
//      contentType(createdResult) mustBe Some("text/html")
//      contentAsString(createdResult) must include ("Jake-Raffe")
//    }
//    "return a BadAPIResponse if username does not correspond to a user" in {
//      val badRequest: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/users/wrongus3rname")
//      (mockGithubService.getUser(_: String)(_: ExecutionContext))
//        .expects("wrongus3rname", executionContext)
//        .returning(Future(Left(APIError.BadAPIResponse(404, "User not found"))))
//        .once()
//      val createdBadResult: Future[Result] = testHomeController.getUser("wrongus3rname")(badRequest)
//      println(createdBadResult)
//      status(createdBadResult) shouldBe Status.OK
//      contentType(createdBadResult) mustBe Some("text/html")
//      contentAsString(createdBadResult) must include ("Unable to find wrongus3rname")
//    }
//  }
//
//  "HomeController .getUserRepositories" should {
//    "get a user's repositories by their username and render a list of their repo details on a webpage" in {
//      val request: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/users/jake-raffe/repos")
//      (mockGithubService.getUserRepo(_: String)(_: ExecutionContext))
//        .expects("jake-raffe", executionContext)
//        .returning(Future(Right(jakeRepos)))
//        .once()
//      val createdResult: Future[Result] = testHomeController.getUserRepositories("jake-raffe")(request)
//
//      status(createdResult) shouldBe Status.OK
//      contentType(createdResult) mustBe Some("text/html")
//      contentAsString(createdResult) must include ("Back-end_Project")
//    }
//    "return a BadAPIResponse if username does not correspond to a user" in {
//      val badRequest: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/users/wrongus3rname/repos")
//      (mockGithubService.getUserRepo(_: String)(_: ExecutionContext))
//        .expects("wrongus3rname", executionContext)
//        .returning(Future(Left(APIError.BadAPIResponse(400, "Unable to access/validate repositories"))))
//        .once()
//      val createdBadResult: Future[Result] = testHomeController.getUserRepositories("wrongus3rname")(badRequest)
//      println(createdBadResult)
//      status(createdBadResult) shouldBe Status.OK
//      contentType(createdBadResult) mustBe Some("text/html")
//      contentAsString(createdBadResult) must include ("Unable to find wrongus3rname&#x27;s repo")
//    }
//  }
//
//  "HomeController .getUserRepositoryContents" should {
//    "get the contents of a user's repository by their username & repo name, and render a list of the contents on a webpage" in {
//      val request: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/repos/jake-raffe/Back-end_Project/contents")
//      (mockGithubService.getRepoContents(_: String, _: String)(_: ExecutionContext))
//        .expects("jake-raffe", "Back-end_Project", executionContext)
//        .returning(Future(Right(repoContents)))
//        .once()
//      val createdResult: Future[Result] = testHomeController.getUserRepositoryContents("jake-raffe", "Back-end_Project")(request)
//
//      status(createdResult) shouldBe Status.OK
//      contentType(createdResult) mustBe Some("text/html")
//      contentAsString(createdResult) must include (".idea")
//    }
//    "return a BadAPIResponse if username does not correspond to a user" in {
//      val badRequest: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/repos/jake-raffe/wrongRepo/contents")
//      (mockGithubService.getRepoContents(_: String, _: String)(_: ExecutionContext))
//        .expects("jake-raffe", "wrongRepo", executionContext)
//        .returning(Future(Left(APIError.BadAPIResponse(400, "Unable to validate content in repository"))))
//        .once()
//      val createdBadResult: Future[Result] = testHomeController.getUserRepositoryContents("jake-raffe", "wrongRepo")(badRequest)
//      println(createdBadResult)
//      status(createdBadResult) shouldBe Status.OK
//      contentType(createdBadResult) mustBe Some("text/html")
//      contentAsString(createdBadResult) must include ("Unable to find jake-raffe/wrongRepo contents")
//    }
//  }
//
//  "HomeController .getUserRepositoryContentsPath" should {
//    "get the contents of a repository's directory by the URL path, and render the contents on a webpage" in {
//      val request: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/repos/jake-raffe/Back-end_Project/contents/src")
//      (mockGithubService.getRepoContentsPath(_: String, _: String, _: String)(_: ExecutionContext))
//        .expects("jake-raffe", "Back-end_Project", "/src", executionContext)
//        .returning(Future(Right(directoryContents)))
//        .once()
//      val createdResult: Future[Result] = testHomeController.getUserRepositoryContentsPath("jake-raffe", "Back-end_Project", "/src")(request)
//
//      status(createdResult) shouldBe Status.OK
//      contentType(createdResult) mustBe Some("text/html")
//      contentAsString(createdResult) must include ("test")
//    }
//    "return a BadAPIResponse if path cannot be found" in {
//      val badRequest: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/repos/jake-raffe/Back-end_Project/contents/bluebell")
//      (mockGithubService.getRepoContentsPath(_: String, _: String, _: String)(_: ExecutionContext))
//        .expects("jake-raffe", "Back-end_Project", "bluebell", executionContext)
//        .returning(Future(Left(APIError.BadAPIResponse(400, "Unable to validate content in repository, this is the deeper level one"))))
//        .once()
//      val createdBadResult: Future[Result] = testHomeController.getUserRepositoryContentsPath("jake-raffe", "Back-end_Project", "bluebell")(badRequest)
//      println(createdBadResult)
//      status(createdBadResult) shouldBe Status.OK
//      contentType(createdBadResult) mustBe Some("text/html")
//      contentAsString(createdBadResult) must include ("Unable to find jake-raffe/Back-end_Project/bluebell contents")
//    }
//  }
//
//  "HomeController .getFileContents" should {
//    "get the contents of a file and render it in plain text on a webpage" in {
//      val request: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/repos/jake-raffe/Back-end_Project/contents/.idea/.gitignore/open")
//      (mockGithubService.getFileContents(_: String, _: String, _: String)(_: ExecutionContext))
//        .expects("jake-raffe", "Back-end_Project", "/.idea/.gitignore", executionContext)
//        .returning(Future(Right(fileContentString)))
//        .once()
//      val createdResult: Future[Result] = testHomeController.getFileContents("jake-raffe", "Back-end_Project", "/.idea/.gitignore")(request)
//
//      status(createdResult) shouldBe Status.OK
//      contentType(createdResult) mustBe Some("text/html")
//      contentAsString(createdResult) must include ("shelf")
//    }
//    "return a BadAPIResponse if path cannot be found" in {
//      val badRequest: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/repos/jake-raffe/Back-end_Project/contents/bluebell")
//      (mockGithubService.getFileContents(_: String, _: String, _: String)(_: ExecutionContext))
//        .expects("jake-raffe", "Back-end_Project", "bluebell", executionContext)
//        .returning(Future(Left(APIError.BadAPIResponse(400, "Unable to return file contents at connector"))))
//        .once()
//      val createdBadResult: Future[Result] = testHomeController.getFileContents("jake-raffe", "Back-end_Project", "bluebell")(badRequest)
//      println(createdBadResult)
//      status(createdBadResult) shouldBe Status.OK
//      contentType(createdBadResult) mustBe Some("text/html")
//      contentAsString(createdBadResult) must include ("Unable to return file contents at connector")
//    }
//  }

}
