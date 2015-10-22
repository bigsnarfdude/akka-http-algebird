import com.twitter.algebird.HyperLogLog
import com.twitter.algebird.HyperLogLogMonoid
import com.twitter.algebird.HLL

object HLLMonoid {
 
    def loadListInt(data: List[Int]): com.twitter.algebird.HLL = {
      import com.twitter.algebird.HyperLogLog.int2Bytes
      val hll = new HyperLogLogMonoid(12)
      val seqHll = data.map { hll(_) }
      val returnHll = hll.sum(seqHll)
      returnHll
    }

    def loadListLong(data: List[Long]): com.twitter.algebird.HLL = {
      import com.twitter.algebird.HyperLogLog.long2Bytes
      val hll = new HyperLogLogMonoid(12)
      val seqHll = data.map { hll(_) }
      val returnHll = hll.sum(seqHll)
      returnHll
    }

    def loadListString(data: List[String]): com.twitter.algebird.HLL = {
      implicit def string2Bytes(i : String) = i.toCharArray.map(_.toByte)
      val hll = new HyperLogLogMonoid(12)
      val seqHll = data.map { hll(_) }
      val returnHll = hll.sum(seqHll)
      returnHll
    }

}