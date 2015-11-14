import com.twitter.algebird.HyperLogLog
import com.twitter.algebird.HyperLogLogMonoid
import com.twitter.algebird.HLL
import org.apache.commons.codec.binary.Base64

object HLLSerializer {

    private val kryo = KryoSerializer.kryo
    private val MAGIC = "%%%"

    def fromMagicString(hllHash:String): com.twitter.algebird.HLL = {
      val trimmed = unMAGIC(hllHash)
      // decode the string into bytes
      val bytes = Base64.decodeBase64(trimmed)
      // hyperll is a Hyperloglog data structure
      val hyperll = kryo.fromBytes(bytes).asInstanceOf[HLL]
      // return hyperll
      hyperll
    }

    def toMagicString(hll: HLL): String = {
      // takes hll and converts to bytes
      val bytes: Array[Byte] = kryo.toBytesWithClass(hll)
      // encode to Base64
      val encoded = Base64.encodeBase64(bytes)
      // create String object as Base64 encoded HLL
      val serialized = new String(encoded)
      MAGIC + serialized
    }

    def unMAGIC(serialized: String): String = {
        if(serialized.startsWith(MAGIC))
            serialized.drop(MAGIC.size)
        else
            "None"
    }
}