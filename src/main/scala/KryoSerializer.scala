import com.twitter.chill.algebird.AlgebirdRegistrar
import com.twitter.chill.{KryoPool, ScalaKryoInstantiator}


object KryoSerializer {
  val KryoPoolSize = 10
  val kryo = {
    val inst = () ⇒ {
      val newK = (new ScalaKryoInstantiator).newKryo()
      newK.setReferences(false)
      (new AlgebirdRegistrar).apply(newK)
      newK
    }
    KryoPool.withByteArrayOutputStream(KryoPoolSize, inst)
  }
}

