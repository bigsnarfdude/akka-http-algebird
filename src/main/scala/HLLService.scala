

// Java
import java.util.Date
import java.util.TimeZone
import java.text.SimpleDateFormat

// Scala
import awscala._
import awscala.dynamodbv2._
import awscala.dynamodbv2.GlobalSecondaryIndex
import com.amazonaws.services.{ dynamodbv2 => aws }


/**
 * EventService Object holds all the functions for Redis Access
 */
object EventService {

  // sets DynamoDB client to us-east-1
  //implicit val dynamoDB = DynamoDB.at(Region.US_EAST_1)

  // sets dynamodb table name
  //val tablename = "my-table11"

  // sets up table and dynamodb connection
  //val table: Table = dynamoDB.table(tablename).get

  // sets various settings for global secondary index
  //val dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  //val timezone = TimeZone.getTimeZone("UTC")
  //val Hash = aws.model.KeyType.HASH
  //val Range = aws.model.KeyType.RANGE
  //val Include = aws.model.ProjectionType.INCLUDE
  //val All = aws.model.ProjectionType.ALL

  // sets up table access to global secondary index with read write 10
  //val globalSecondaryIndex = GlobalSecondaryIndex(
  //  name = "CountsIndex",
  //  keySchema = Seq(KeySchema("EventType", Hash), KeySchema("Timestamp", Range)),
  //  projection = Projection(All),
  //  provisionedThroughput = ProvisionedThroughput(readCapacityUnits = 10, writeCapacityUnits = 10)
  //)

}
