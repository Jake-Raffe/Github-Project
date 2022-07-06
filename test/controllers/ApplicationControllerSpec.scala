package controllers

import baseSpec.BaseSpecWithApplication
import cats.data.EitherT
import models.APIError.BadAPIResponse
import models.{APIError, User}
import org.junit.Test.None
import org.scalamock.matchers.Matchers
import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.test.{FakeRequest, Injecting}
import play.api.http.Status
import play.api.libs.json.{JsValue, Json, OFormat}
import play.api.mvc.Results.Created
import play.api.mvc.{Action, AnyContentAsEmpty, Request, Result}
import play.api.test.Helpers.{await, contentAsJson, defaultAwaitTimeout, status}
import repositories.DataRepository
import services.ApplicationService
import uk.gov.hmrc.mongo.MongoComponent

import scala.concurrent.{ExecutionContext, Future}

class ApplicationControllerSpec extends BaseSpecWithApplication with MockFactory with ScalaFutures with Matchers {
// with Injecting with GuiceOneAppPerSuite {

  override implicit lazy val app: Application = fakeApplication()
  val integrationTestApplicationController = new ApplicationController(
    component,
    githubService,
    applicationService,
    executionContext
  )

  private val mockUser: User = User(
    "jake-raffe",
    "2022-01-07T19:56:27Z",
    Some("London"),
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

  // Index good
  "ApplicationController .index()" should {
    val result = integrationTestApplicationController.index()(FakeRequest())
    "return status OK" in {
      status(result)(defaultAwaitTimeout) shouldBe Status.OK
    }
  }

  // Create good
  "ApplicationController .create()" should {
    beforeEach()
    "create a book in the database" in {
      val request: FakeRequest[JsValue] = buildPost("/github/users").withBody[JsValue](Json.toJson(mockUser))
      val createdResult: Future[Result] = integrationTestApplicationController.create()(request)
      println(Json.prettyPrint(contentAsJson(createdResult)))
      status(createdResult)(defaultAwaitTimeout) shouldBe Status.CREATED
      contentAsJson(createdResult)(defaultAwaitTimeout).as[JsValue] shouldBe Json.toJson(mockUser)
    }
    "return BadRequest if request body is of wrong format" in {
      val emptyRequest: FakeRequest[JsValue] = buildPost("/github/users").withBody[JsValue](Json.obj())
      val createdEmptyResult: Future[Result] = integrationTestApplicationController.create()(emptyRequest)
      status(createdEmptyResult)(defaultAwaitTimeout) shouldBe Status.INTERNAL_SERVER_ERROR
      contentAsJson(createdEmptyResult)(defaultAwaitTimeout) shouldBe Json.toJson("Bad response from upstream; Status: 415, Reason: Unable to validate request body format")
    }
  }

  // Read good
  "ApplicationController .read(username: String)" should {

    "find a user in the database by their login" in {
      val request: FakeRequest[JsValue] = buildPost("/github").withBody[JsValue](Json.toJson(mockUser))
      integrationTestApplicationController.create()(request)

      val readRequest: FakeRequest[AnyContentAsEmpty.type] = buildGet("/github/:username")
      val readResult: Future[Result] = integrationTestApplicationController.read("jake-raffe")(readRequest)
      status(readResult)(defaultAwaitTimeout) shouldBe Status.OK
      contentAsJson(readResult)(defaultAwaitTimeout).as[JsValue] shouldBe Json.toJson(mockUser)
    }
    "return a NotFound if a user of the username is not found" in {
      val readRequest: FakeRequest[AnyContentAsEmpty.type] = buildGet("/github/:username")
      val readWrongResult: Future[Result] = integrationTestApplicationController.read("wrong-login")(readRequest)
      status(readWrongResult)(defaultAwaitTimeout) shouldBe Status.INTERNAL_SERVER_ERROR
      contentAsJson(readWrongResult)(defaultAwaitTimeout) shouldBe Json.toJson("Bad response from upstream; Status: 404, Reason: Unable to find user of username: wrong-login")
    }
  }

  // Update good
  "ApplicationController .update(username: String)" should {

    "find a user in the database by their login and replace them with an updated user" in {
      beforeEach()
      val request: FakeRequest[JsValue] = buildPost("/github").withBody[JsValue](Json.toJson(mockUser))
      val createdRequest = integrationTestApplicationController.create()(request)
      await(createdRequest)
      val updateRequest: FakeRequest[JsValue] = buildPut("/github/:username").withBody[JsValue](Json.toJson(updatedMockUser))
      val updateResult: Future[Result] = integrationTestApplicationController.update("jake-raffe")(updateRequest)

      status(updateResult)(defaultAwaitTimeout) shouldBe Status.ACCEPTED
      contentAsJson(updateResult)(defaultAwaitTimeout).as[JsValue] shouldBe Json.toJson(updatedMockUser)
    }

    "return BadAPIResponse message if wrong login" in {
      val updateRequest: FakeRequest[JsValue] = buildPut("/github/:username").withBody[JsValue](Json.toJson(updatedMockUser))
      val updateWrongIDResult: Future[Result] = integrationTestApplicationController.update("125")(updateRequest)
      status(updateWrongIDResult)(defaultAwaitTimeout) shouldBe Status.INTERNAL_SERVER_ERROR
      contentAsJson(updateWrongIDResult)(defaultAwaitTimeout) shouldBe Json.toJson("Bad response from upstream; Status: 400, Reason: Unable to update user of username: 125")
    }

    "return BadRequest if incorrect Json body" in {
      val request: FakeRequest[JsValue] = buildPost("/github").withBody[JsValue](Json.toJson(mockUser))
      integrationTestApplicationController.create()(request)
      val emptyUpdateRequest: FakeRequest[JsValue] = buildPut("/github/:username").withBody[JsValue](Json.obj())
      val emptyUpdateResult: Future[Result] = integrationTestApplicationController.update("jake-raffe")(emptyUpdateRequest)
      status(emptyUpdateResult)(defaultAwaitTimeout) shouldBe Status.BAD_REQUEST
    }
  }

  // Delete good
  "ApplicationController .delete(username: String)" should {
    "find a user in the database by their login and delete it" in {
      beforeEach()
      val request: FakeRequest[JsValue] = buildPost("/github").withBody[JsValue](Json.toJson(mockUser))
      val createdResult = integrationTestApplicationController.create()(request)
      await(createdResult)
      val deleteRequest: FakeRequest[AnyContentAsEmpty.type] = buildDelete("/github/:username")
      val deleteResult: Future[Result] = integrationTestApplicationController.delete("jake-raffe")(deleteRequest)
      status(deleteResult)(defaultAwaitTimeout) shouldBe Status.ACCEPTED
    }
    "return a BadAPIResponse if the username does not exist" in {
      val deleteWrongIdRequest: FakeRequest[AnyContentAsEmpty.type] = buildDelete("/github/:username")
      val deleteWrongIdResult: Future[Result] = integrationTestApplicationController.delete("5")(deleteWrongIdRequest)
      status(deleteWrongIdResult)(defaultAwaitTimeout) shouldBe Status.INTERNAL_SERVER_ERROR
      contentAsJson(deleteWrongIdResult)(defaultAwaitTimeout) shouldBe Json.toJson("Bad response from upstream; Status: 400, Reason: Unable to delete user of username: 5")
    }
  }

//  override def beforeEach(): Unit = {  }
  override def beforeEach(): Unit = repository.deleteAll()
//  override def afterEach(): Unit = repository.deleteAll()
}
