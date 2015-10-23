

// Java
import java.util.Date
import java.util.TimeZone
import java.text.SimpleDateFormat

// Scala
import awscala._
import awscala.dynamodbv2._
import awscala.dynamodbv2.GlobalSecondaryIndex
import com.amazonaws.services.{ dynamodbv2 => aws }


import com.redis._
import serialization._
import Parse.Implicits._
import Parse.Implicits.parseByteArray



/**
 * HLLService Object holds all the functions for Redis Access
 */
object HLLService {

  private val r = new RedisClient("localhost", 6379)

  def put(hll_key: String, hll_string: String): Boolean = {
    r.set(hll_key, hll_string)
  }

  def get(hll_key: String): Option[String] = {
    r.get[String](hll_key)
  }

}
