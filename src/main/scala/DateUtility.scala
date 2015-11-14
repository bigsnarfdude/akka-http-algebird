
// Java
import java.util.Date
import java.text.SimpleDateFormat

/**
 * Object uses downsampling method to create metadata from each
 * EventType log record. Parsing the ISO 8601
 * datetime stamp to the minute means downsampling aka reducing
 * precision.
 *
 * Bucketing
 * A family of aggregations that build buckets, where each bucket
 * is associated with a key and an EventType criterion. When the
 * aggregation is executed, all the buckets criteria are evaluated
 * on every EventType in the context and when a criterion matches,
 * the EventType is considered to "fall in" the relevant bucket.
 * By the end of the aggregation process, we’ll end up with a
 * list of buckets - each one with a set of EventTypes that
 * "belong" to it.
 *
 */
object DateUtility {

  private val BucketToMinuteFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:00.000")

  /**
   * Function to bucket a date based on
   * our bucketing strategy. Bucketing
   * means downsampling aka reducing
   * precision.
   *
   * @param date The Java Date to bucket
   * @return the downsampled date in String
   *         format
   */
  def bucket(date: Date): String =
    BucketToMinuteFormatter.format(date)
}