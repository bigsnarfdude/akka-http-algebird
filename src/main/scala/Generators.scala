object Generator {


    def uuid = java.util.UUID.randomUUID.toString

    def random_uuids(numbersRequired: Int): scala.collection.immutable.IndexedSeq[String] = {
      for (i <- 1 to numbersRequired) 
        yield uuid
    }
    
    val oneMillionUUIDS = random_uuids(1000000)
     
    val hllHash = "AQBjb20udHdpdHRlci5hbGdlYmlyZC5TcGFyc2VITMwRAww9CAGyBQJiBwKUCAJcDQE="

    val data1 = List(1, 1, 2, 2, 3, 3, 4, 4, 5, 5)
    val data2 = List(1L,1L,2L,2L,3L,3L,4L,4L,5L,5L)
    val data3 = List("1","1","2","3","3","4","4","5","5")

    def random_numbers(numbersRequired: Int, rangeNumber: Int) = {
      var generator = new scala.util.Random
      1 to numbersRequired map { _ => generator.nextInt(rangeNumber) }
    }

    // 100,000 different numbers
    val oneMillionRandomNumbers = random_numbers(1000000, 100000).toList
    val moarRandomNumbers = random_numbers(100000, 200000).toList
    val unqiueNumbers = oneMillionRandomNumbers.toSet.size
    val overlappingNUmbers = moarRandomNumbers.toSet.size
}