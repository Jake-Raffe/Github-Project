package controllers

import baseSpec.BaseSpecWithApplication
import models.APIError.BadAPIResponse
import models.{APIError, User}
import org.scalamock.scalatest.MockFactory
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AnyContentAsEmpty, Request, Result}
import play.api.mvc.Results.{Accepted, Created, Ok}
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers.{await, contentAsJson, defaultAwaitTimeout, status}
import services.{ApplicationService, GithubService}

import scala.concurrent.Future

class ApplicationControllerUnitSpec extends BaseSpecWithApplication with MockFactory{

  val mockApplicationService = mock[ApplicationService]
  val mockGithubService = mock[GithubService]

  val unitTestApplicationController = new ApplicationController(
    component,
    mockGithubService,
    mockApplicationService,
    executionContext
  )

  private val mockUser: User = User(
    "jake-raffe",
    "2022-01-07T19:56:27Z",
    None,
    100,
    20
  )

  private val updatedMockUser: User = User(
    mockUser.login,
    "2022-01-07T19:56:27Z",
    Some("London"),
    200,
    200
  )

  "ApplicationController .create()" should {
      "return Created with the user that has been added to the database" in {
        val request: FakeRequest[JsValue] = buildPost("/github/users").withBody[JsValue](Json.toJson(mockUser))
        (mockApplicationService.create(_: Request[JsValue]))
          .expects(request)
          .returning(Future(Right(mockUser)))
          .once()
        val createdResult: Future[Result] = unitTestApplicationController.create()(request)

        status(createdResult)(defaultAwaitTimeout) shouldBe Status.CREATED
        contentAsJson(createdResult).as[User] shouldBe mockUser
      }

      "return a BadAPIResponse if the request body is of the wrong format" in {
        val badRequest: FakeRequest[JsValue] = buildPost("/github/users").withBody[JsValue](Json.obj())
        (mockApplicationService.create(_: Request[JsValue]))
          .expects(badRequest)
          .returning(Future(Left(APIError.BadAPIResponse(415, "Unable to validate request body format"))))
          .once()
        val badCreatedResult: Future[Result] = unitTestApplicationController.create()(badRequest)

        status(badCreatedResult)(defaultAwaitTimeout) shouldBe Status.INTERNAL_SERVER_ERROR
        contentAsJson(badCreatedResult)(defaultAwaitTimeout) shouldBe Json.toJson("Bad response from upstream; Status: 415, Reason: Unable to validate request body format")
      }
    }

  "ApplicationController .read(username: String)" should {

    "find a user in the database by their login" in {
      val readRequest: FakeRequest[AnyContentAsEmpty.type] = buildGet("/github/:username")
      (mockApplicationService.read(_: String))
        .expects("jake-raffe")
        .returning(Future(Right(Ok(Json.toJson(mockUser)))))
        .once()
      val readResult: Future[Result] = unitTestApplicationController.read("jake-raffe")(readRequest)

      status(readResult)(defaultAwaitTimeout) shouldBe Status.OK
      contentAsJson(readResult)(defaultAwaitTimeout).as[JsValue] shouldBe Json.toJson(mockUser)
    }
    "return a NotFound if a user of the login is not found" in {
      val readRequest: FakeRequest[AnyContentAsEmpty.type] = buildGet("/github/:username")
      (mockApplicationService.read(_: String))
        .expects("wrong-login")
        .returning(Future(Left(BadAPIResponse(404, "Unable to find user of username: wrong-login"))))
        .once()
      val readWrongIDResult: Future[Result] = unitTestApplicationController.read("wrong-login")(readRequest)

      status(readWrongIDResult)(defaultAwaitTimeout) shouldBe Status.INTERNAL_SERVER_ERROR
      contentAsJson(readWrongIDResult)(defaultAwaitTimeout) shouldBe Json.toJson("Bad response from upstream; Status: 404, Reason: Unable to find user of username: wrong-login")
    }
  }

  "ApplicationController .update(username: String)" should {

    "find a user in the database by their login and replace them with an updated user" in {
      val updateRequest: FakeRequest[JsValue] = buildPut("/github/:username").withBody[JsValue](Json.toJson(updatedMockUser))
      (mockApplicationService.update(_: String, _: User))
        .expects("jake-raffe", updatedMockUser)
        .returning(Future(Right(Accepted(Json.toJson(updatedMockUser)))))
        .once()
      val updateResult: Future[Result] = unitTestApplicationController.update("jake-raffe")(updateRequest)

      status(updateResult)(defaultAwaitTimeout) shouldBe Status.ACCEPTED
      contentAsJson(updateResult)(defaultAwaitTimeout).as[JsValue] shouldBe Json.toJson(updatedMockUser)
    }

    "return BadAPIResponse message if wrong ID" in {
      val updateRequest: FakeRequest[JsValue] = buildPut("/github/:username").withBody[JsValue](Json.toJson(updatedMockUser))
      (mockApplicationService.update(_: String, _: User))
        .expects("125", updatedMockUser)
        .returning(Future(Left(APIError.BadAPIResponse(400, "Unable to update user of username: 125"))))
        .once()
      val updateResult: Future[Result] = unitTestApplicationController.update("125")(updateRequest)

      status(updateResult)(defaultAwaitTimeout) shouldBe Status.INTERNAL_SERVER_ERROR
      contentAsJson(updateResult)(defaultAwaitTimeout).as[JsValue] shouldBe Json.toJson("Bad response from upstream; Status: 400, Reason: Unable to update user of username: 125")
    }

    "return BadRequest if incorrect Json body" in {
      val emptyUpdateRequest: FakeRequest[JsValue] = buildPut("/username/:username").withBody[JsValue](Json.obj())
      val emptyUpdateResult: Future[Result] = unitTestApplicationController.update("jake-raffe")(emptyUpdateRequest)

      status(emptyUpdateResult)(defaultAwaitTimeout) shouldBe Status.BAD_REQUEST
    }
  }

  "ApplicationController .delete(username: String)" should {
    "find a user in the database by their login and delete it" in {
      val deleteRequest: FakeRequest[AnyContentAsEmpty.type] = buildDelete("/github/:username")
      (mockApplicationService.delete(_: String))
        .expects("jake-raffe")
        .returning(Future(Right(Accepted)))
        .once()
      val deleteResult: Future[Result] = unitTestApplicationController.delete("jake-raffe")(deleteRequest)

      status(deleteResult)(defaultAwaitTimeout) shouldBe Status.ACCEPTED
    }
    "return a BadAPIResponse if the login does not exist" in {
      val deleteWrongIdRequest: FakeRequest[AnyContentAsEmpty.type] = buildDelete("/github/:username")
      (mockApplicationService.delete(_: String))
        .expects("5")
        .returning(Future(Left(BadAPIResponse(400, "Unable to delete user of username: 5"))))
        .once()
      val deleteWrongIdResult: Future[Result] = unitTestApplicationController.delete("5")(deleteWrongIdRequest)

      status(deleteWrongIdResult)(defaultAwaitTimeout) shouldBe Status.INTERNAL_SERVER_ERROR
      contentAsJson(deleteWrongIdResult)(defaultAwaitTimeout) shouldBe Json.toJson("Bad response from upstream; Status: 400, Reason: Unable to delete user of username: 5")
    }
  }
}
