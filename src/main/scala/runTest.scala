import com.redis._

object runTest {
    
    val r = new RedisClient("localhost", 6379)

    def runTest(numbersRequired: Int){
      for (i <- 1 to numbersRequired) {
        val numbs = Generator.oneMillionRandomNumbers
        val hll = HLLMonoid.loadListInt(numbs)
        val hll_string = HLLSerializer.toMagicString(hll)
        val key = s"loginService-2015-08-14T12:5$i:00.000"
        println(HLLService.put(key, hll_string))
        println(key)
        }
    }

    // 1000 HLL with 1 Million unique UUIDs
    runTest(1000)



    def scan(searchTerm: String):
      Option[(Option[Int], Option[List[Option[String]]])] = {
      r.scan(1, searchTerm, 10)    
    }

}