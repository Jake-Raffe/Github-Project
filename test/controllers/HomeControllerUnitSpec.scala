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

  val jakeUser = new User(
    "Jake-Raffe", "2022-01-07T19:56:27Z", None, 4,5)
  val jakeRepos = List(
    new Repository("Back-end_Project"), new Repository("Flight-Booking-CLI-Project"))
  val repoContents = List(
    new Content(".idea", "dir", "", ""), new Content("README.md", "file", "", ""))
  val directoryContents = List(
    new Content("main", "dir", "src", ""), new Content("test", "dir", "src", ""))
  val fileContentString = """# Default ignored files\n/shelf/\n/workspace.xml"""

  "HomeController .getUser" should {

    "get a user by their username and render their details on a webpage" in {
      val request: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/users/jake-raffe")
      (mockGithubService.getUser(_: String)(_: ExecutionContext))
        .expects("jake-raffe", executionContext)
        .returning(Future(Right(jakeUser)))
        .once()
      val createdResult: Future[Result] = testHomeController.getUser("jake-raffe")(request)

      status(createdResult) shouldBe Status.OK
      contentType(createdResult) mustBe Some("text/html")
      contentAsString(createdResult) must include ("Jake-Raffe")
    }
    "return a BadAPIResponse if username does not correspond to a user" in {
      val badRequest: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/users/wrongus3rname")
      (mockGithubService.getUser(_: String)(_: ExecutionContext))
        .expects("wrongus3rname", executionContext)
        .returning(Future(Left(APIError.BadAPIResponse(404, "User not found"))))
        .once()
      val createdBadResult: Future[Result] = testHomeController.getUser("wrongus3rname")(badRequest)
      println(createdBadResult)
      status(createdBadResult) shouldBe Status.OK
      contentType(createdBadResult) mustBe Some("text/html")
      contentAsString(createdBadResult) must include ("Unable to find wrongus3rname")
    }
  }

  "HomeController .getUserRepositories" should {
    "get a user's repositories by their username and render a list of their repo details on a webpage" in {
      val request: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/users/jake-raffe/repos")
      (mockGithubService.getUserRepo(_: String)(_: ExecutionContext))
        .expects("jake-raffe", executionContext)
        .returning(Future(Right(jakeRepos)))
        .once()
      val createdResult: Future[Result] = testHomeController.getUserRepositories("jake-raffe")(request)

      status(createdResult) shouldBe Status.OK
      contentType(createdResult) mustBe Some("text/html")
      contentAsString(createdResult) must include ("Back-end_Project")
    }
    "return a BadAPIResponse if username does not correspond to a user" in {
      val badRequest: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/users/wrongus3rname/repos")
      (mockGithubService.getUserRepo(_: String)(_: ExecutionContext))
        .expects("wrongus3rname", executionContext)
        .returning(Future(Left(APIError.BadAPIResponse(400, "Unable to access/validate repositories"))))
        .once()
      val createdBadResult: Future[Result] = testHomeController.getUserRepositories("wrongus3rname")(badRequest)
      println(createdBadResult)
      status(createdBadResult) shouldBe Status.OK
      contentType(createdBadResult) mustBe Some("text/html")
      contentAsString(createdBadResult) must include ("Unable to find wrongus3rname&#x27;s repo")
    }
  }

  "HomeController .getUserRepositoryContents" should {
    "get the contents of a repository if at the repo's top level, and render a list of the contents on a webpage" in {
      val request: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/repos/jake-raffe/Back-end_Project/contents/repo-contents/open")
      (mockGithubService.getRepoContents(_: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "Back-end_Project", "", executionContext)
        .returning(Future(Right(repoContents)))
        .once()
      val createdResult: Future[Result] = testHomeController.getUserRepositoryContents("jake-raffe", "Back-end_Project", "repo-contents")(request)

      status(createdResult) shouldBe Status.OK
      contentType(createdResult) mustBe Some("text/html")
      contentAsString(createdResult) must include (".idea")
    }
    "get the contents of a directory and render them on a webpage" in {
      val request: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/repos/jake-raffe/Back-end_Project/contents/src/open")
      (mockGithubService.getRepoContents(_: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "Back-end_Project", "src", executionContext)
        .returning(Future(Right(repoContents)))
        .once()
      val createdResult: Future[Result] = testHomeController.getUserRepositoryContents("jake-raffe", "Back-end_Project", "src")(request)

      status(createdResult) shouldBe Status.OK
      contentType(createdResult) mustBe Some("text/html")
      contentAsString(createdResult) must include (".idea")
    }
    "return a BadAPIResponse if username does not correspond to a user" in {
      val badRequest: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/repos/jake-raffe/wrongRepo/contents/repo-contents/open")
      (mockGithubService.getRepoContents(_: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "wrongRepo", "", executionContext)
        .returning(Future(Left(APIError.BadAPIResponse(400, "Unable to validate content in repository"))))
        .once()
      val createdBadResult: Future[Result] = testHomeController.getUserRepositoryContents("jake-raffe", "wrongRepo", "repo-contents")(badRequest)
      println(createdBadResult)
      status(createdBadResult) shouldBe Status.OK
      contentType(createdBadResult) mustBe Some("text/html")
      contentAsString(createdBadResult) must include ("Unable to find jake-raffe/wrongRepo/repo-contents contents")
    }

    "get the contents of a repository's directory by the URL path, and render the contents on a webpage" in {
      val request: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/repos/jake-raffe/Back-end_Project/contents/src")
      (mockGithubService.getRepoContents(_: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "Back-end_Project", "/src", executionContext)
        .returning(Future(Right(directoryContents)))
        .once()
      val createdResult: Future[Result] = testHomeController.getUserRepositoryContents("jake-raffe", "Back-end_Project", "/src")(request)

      status(createdResult) shouldBe Status.OK
      contentType(createdResult) mustBe Some("text/html")
      contentAsString(createdResult) must include ("test")
    }
    "return a BadAPIResponse if path cannot be found" in {
      val badRequest: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/repos/jake-raffe/Back-end_Project/contents/bluebell")
      (mockGithubService.getRepoContents(_: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "Back-end_Project", "bluebell", executionContext)
        .returning(Future(Left(APIError.BadAPIResponse(400, "Unable to validate content in repository"))))
        .once()
      val createdBadResult: Future[Result] = testHomeController.getUserRepositoryContents("jake-raffe", "Back-end_Project", "bluebell")(badRequest)
      println(createdBadResult)
      status(createdBadResult) shouldBe Status.OK
      contentType(createdBadResult) mustBe Some("text/html")
      contentAsString(createdBadResult) must include ("Unable to find jake-raffe/Back-end_Project/bluebell contents")
    }
  }

  "HomeController .getFileContents" should {
    "get the contents of a file and render it in plain text on a webpage" in {
      val request: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/repos/jake-raffe/Back-end_Project/contents/.idea/.gitignore/open")
      (mockGithubService.getFileContents(_: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "Back-end_Project", "/.idea/.gitignore", executionContext)
        .returning(Future(Right(fileContentString)))
        .once()
      val createdResult: Future[Result] = testHomeController.getFileContents("jake-raffe", "Back-end_Project", "/.idea/.gitignore")(request)

      status(createdResult) shouldBe Status.OK
      contentType(createdResult) mustBe Some("text/html")
      contentAsString(createdResult) must include ("shelf")
    }
    "return a BadAPIResponse if path cannot be found" in {
      val badRequest: FakeRequest[AnyContentAsEmpty.type] = buildPost("/github/repos/jake-raffe/Back-end_Project/contents/bluebell")
      (mockGithubService.getFileContents(_: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "Back-end_Project", "bluebell", executionContext)
        .returning(Future(Left(APIError.BadAPIResponse(400, "Unable to return file contents at connector"))))
        .once()
      val createdBadResult: Future[Result] = testHomeController.getFileContents("jake-raffe", "Back-end_Project", "bluebell")(badRequest)
      println(createdBadResult)
      status(createdBadResult) shouldBe Status.OK
      contentType(createdBadResult) mustBe Some("text/html")
      contentAsString(createdBadResult) must include ("Unable to return file contents at connector")
    }
  }


  "HomeController .createNewFile" should {
    "submit a file to the api, updating the repository at the top level, then redirect to the top level displaying that file" in {
      val request: FakeRequest[AnyContentAsEmpty.type] = buildGet("/github/repos/jake-raffe/Back-end_Project/contents/top/create?fileName=FileName.md&fileContent=This+is+the+file+content")
      (mockGithubService.createNewFile(_: String, _: String, _: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "Back-end_Project", "", "FileName.md", "This is the file content", executionContext)
        .returning(Future(Right("success")))
        .once()
      val result: Future[Result] = testHomeController.createNewFile("jake-raffe", "Back-end_Project", "top")(request)

      status(result) shouldBe Status.SEE_OTHER
    }
    "submit a file to the api, updating the repository to show the file in a directory, then redirect to the directory displaying that file" in {
      val request: FakeRequest[AnyContentAsEmpty.type] = buildGet("/github/repos/jake-raffe/Back-end_Project/contents/src/create?fileName=FileName.md&fileContent=This+is+the+file+content")
      (mockGithubService.createNewFile(_: String, _: String, _: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "Back-end_Project", "src", "FileName.md", "This is the file content", executionContext)
        .returning(Future(Right("success")))
        .once()
      val result: Future[Result] = testHomeController.createNewFile("jake-raffe", "Back-end_Project", "src")(request)

      status(result) shouldBe Status.SEE_OTHER
    }
    "return a BadAPIResponse if path cannot be found" in {
      val badRequest: FakeRequest[AnyContentAsEmpty.type] = buildGet("/github/repos/jake-raffe/Back-end_Project/contents/bluebell/create?fileName=FileName.md&fileContent=This+is+the+file+content")
      (mockGithubService.createNewFile(_: String, _: String, _: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "Back-end_Project", "bluebell", "FileName.md", "This is the file content", executionContext)
        .returning(Future(Left(APIError.BadAPIResponse(404, "Unable to return file contents at connector"))))
        .once()
      val createdBadResult: Future[Result] = testHomeController.createNewFile("jake-raffe", "Back-end_Project", "bluebell")(badRequest)
      println(createdBadResult)
      status(createdBadResult) shouldBe Status.OK
      contentType(createdBadResult) mustBe Some("text/html")
      contentAsString(createdBadResult) must include ("Unable to return file contents at connector")
    }
  }

  "HomeController .updateFile" should {
    "submit a file to the api, updating the repository at the top level, then redirect to the top level displaying that file" in {
      val request: FakeRequest[AnyContentAsEmpty.type] = buildGet("/github/repos/jake-raffe/Back-end_Project/contents/FileName.md/shashasha/update?fileName=FileName.md&fileContent=This+is+the+file+content")
      (mockGithubService.updateFile(_: String, _: String, _: String, _: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "Back-end_Project", "FileName.md", "FileName.md", "This is the file content", "shashasha", executionContext)
        .returning(Future(Right("success")))
        .once()
      val result: Future[Result] = testHomeController.updateFile("jake-raffe", "Back-end_Project", "FileName.md", "shashasha")(request)

      status(result) shouldBe Status.SEE_OTHER
    }
    "submit a file to the api, updating the repository to show the file in a directory, then redirect to the directory displaying that file" in {
      val request: FakeRequest[AnyContentAsEmpty.type] = buildGet("/github/repos/jake-raffe/Back-end_Project/contents/src/FileName.md/shashasha/update?fileName=FileName.md&fileContent=This+is+the+file+content")
      (mockGithubService.updateFile(_: String, _: String, _: String, _: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "Back-end_Project", "src/FileName.md", "FileName.md", "This is the file content", "shashasha", executionContext)
        .returning(Future(Right("success")))
        .once()
      val result: Future[Result] = testHomeController.updateFile("jake-raffe", "Back-end_Project", "src/FileName.md", "shashasha")(request)

      status(result) shouldBe Status.SEE_OTHER
    }
    "return a BadAPIResponse if path cannot be found" in {
      val badRequest: FakeRequest[AnyContentAsEmpty.type] = buildGet("/github/repos/jake-raffe/Back-end_Project/contents/bluebell/FileName.md/shashasha/update?fileName=FileName.md&fileContent=This+is+the+file+content")
      (mockGithubService.updateFile(_: String, _: String, _: String, _: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "Back-end_Project", "bluebell/FileName.md", "FileName.md", "This is the file content", "shashasha", executionContext)
        .returning(Future(Left(APIError.BadAPIResponse(404, "Unable to return file contents at connector"))))
        .once()
      val createdBadResult: Future[Result] = testHomeController.updateFile("jake-raffe", "Back-end_Project", "bluebell/FileName.md", "shashasha")(badRequest)
      println(createdBadResult)
      status(createdBadResult) shouldBe Status.OK
      contentType(createdBadResult) mustBe Some("text/html")
      contentAsString(createdBadResult) must include ("Unable to return file contents at connector")
    }
  }

  "HomeController .deleteFile" should {
    "delete a file that is in the top level of a repository" in {
      val request: FakeRequest[AnyContentAsEmpty.type] = buildGet("/github/repos/jake-raffe/Back-end_Project/contents/top/FileName.md/shashasha/delete")
      (mockGithubService.deleteFile(_: String, _: String, _: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "Back-end_Project", "", "FileName.md", "shashasha", executionContext)
        .returning(Future(Right("success")))
        .once()
      val result: Future[Result] = testHomeController.deleteFile("jake-raffe", "Back-end_Project", "top", "FileName.md", "shashasha")(request)

      status(result) shouldBe Status.SEE_OTHER
    }
    "delete a file that is in a directory within a repository" in {
      val request: FakeRequest[AnyContentAsEmpty.type] = buildGet("/github/repos/jake-raffe/Back-end_Project/contents/src/FileName.md/shashasha/delete")
      (mockGithubService.deleteFile(_: String, _: String, _: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "Back-end_Project", "src", "FileName.md", "shashasha", executionContext)
        .returning(Future(Right("success")))
        .once()
      val result: Future[Result] = testHomeController.deleteFile("jake-raffe", "Back-end_Project", "src", "FileName.md", "shashasha")(request)

      status(result) shouldBe Status.SEE_OTHER
    }
    "return a BadAPIResponse if path cannot be found" in {
      val badRequest: FakeRequest[AnyContentAsEmpty.type] = buildGet("/github/repos/jake-raffe/Back-end_Project/contents/bluebell/FileName.md/shashasha/delete")
      (mockGithubService.deleteFile(_: String, _: String, _: String, _: String, _: String)(_: ExecutionContext))
        .expects("jake-raffe", "Back-end_Project", "bluebell", "FileName.md", "shashasha", executionContext)
        .returning(Future(Left(APIError.BadAPIResponse(404, "Unable to return file contents at connector"))))
        .once()
      val deleteBadResult: Future[Result] = testHomeController.deleteFile("jake-raffe", "Back-end_Project", "bluebell", "FileName.md", "shashasha")(badRequest)
      println(deleteBadResult)
      status(deleteBadResult) shouldBe Status.OK
      contentType(deleteBadResult) mustBe Some("text/html")
      contentAsString(deleteBadResult) must include ("Unable to return file contents at connector")
    }
  }


  "HomeController .filterPath" should {
    "return '' if path == 'top' or 'repo-contents' " in {
      HomeController.filterPath("top") shouldEqual("")
      HomeController.filterPath("repo-contents") shouldEqual("")
    }
    "return 'repo-contents' if path == '' " in {
      HomeController.filterPath("") shouldEqual("repo-contents")
    }
    "remove the '/' from the beginning of path if present" in {
      HomeController.filterPath("/example") shouldEqual("example")
    }
    "return path unchanged if none of the above conditions are present" in {
      HomeController.filterPath("example/path") shouldEqual("example/path")
    }
  }
}
