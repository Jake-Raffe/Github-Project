package repositories

import akka.actor.Status.Success
import com.google.inject.ImplementedBy
import com.mongodb.client.result.InsertOneResult
import models.APIError.BadAPIResponse
import models.{APIError, User}
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.model.Updates.set
import org.mongodb.scala.model.Filters.{empty, equal}
import org.mongodb.scala.model._
import org.mongodb.scala.result
import play.api.libs.json.{JsValue, Json}
import uk.gov.hmrc.mongo.MongoComponent
import uk.gov.hmrc.mongo.play.json.PlayMongoRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.duration.SECONDS
import scala.concurrent.{ExecutionContext, Future}

@ImplementedBy(classOf[DataRepository])
trait DataRepositoryTrait {
  def index(): Future[Either[APIError, Seq[JsValue]]]
  def create(user: User): Future[Either[APIError, User]]
  def read(username: String): Future[User]
  def update(login: String, user: User): Future[Either[APIError, User]]
//  def edit(login: String, fieldName: String, edit: String): Future[Option[User]]
  def delete(login: String): Future[Long]
}

@Singleton
class DataRepository @Inject()(mongoComponent: MongoComponent)(implicit ec: ExecutionContext) extends PlayMongoRepository[User](
  collectionName = "users",
  mongoComponent = mongoComponent,
  domainFormat = User.formats,
  indexes = Seq(IndexModel(
    Indexes.ascending("login"), IndexOptions().unique(true)))
  ) with DataRepositoryTrait {

  val emptyData = new User("empty", "", None, 0, 0)
  val errorData = new User("error", "", None, 0, 0)

  def index(): Future[Either[APIError, Seq[JsValue]]] = {
    collection.find().toFuture().map{
      case users: Seq[User] => Right(users.map(user => Json.toJson(user)))
      case _ => Left(APIError.BadAPIResponse(400, "Unknown error"))
    }
  }

  def create(user: User): Future[Either[APIError, User]] =
    collection.insertOne(user).toFutureOption().map {
      case Some(result: InsertOneResult) if result.wasAcknowledged() => Right(user)
      case _ => Left(APIError.BadAPIResponse(400, "Bad Request"))
    }

  private def byID(id: String): Bson = {
    Filters.and(
      Filters.equal("_id", id)
    )
  }

  private def byUsername(username: String): Bson =
    Filters.and(
      Filters.equal("login", username)
    )

  def read(username: String): Future[User] = {
    collection.find(byUsername(username)).headOption() flatMap {
      case Some(data) => Future(data)
      case _ => Future(emptyData)
    }
  }

  def update(username: String, user: User): Future[Either[APIError, User]] = {
    collection.replaceOne(
      filter = byUsername(username),
      replacement = user,
      options = new ReplaceOptions().upsert(false)
    ).toFutureOption().map{
      case Some(value) if value.wasAcknowledged() => Right(user)
      case _ => Left(APIError.BadAPIResponse(400, s"Unable to update user of username: $username"))
    }
  }

  def delete(username: String): Future[Long] =
    collection.deleteOne(filter = byUsername(username)).toFuture().map(_.getDeletedCount)

  def deleteAll(): Future[Unit] = collection.deleteMany(empty()).toFuture().map(_ => ())

}