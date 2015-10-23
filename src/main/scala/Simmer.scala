import scala.collection.mutable.LinkedHashMap



class Simmer(output: Output, capacity: Int, flushEvery: Option[Int]) {

    Runtime.getRuntime.addShutdownHook(new Thread { override def run { flush } })

    // sets up LinkedHashMap with capicaity of 5000 keys
    // when new value comes in it creates a new agg below in update function
    // accumulators.put(key, newAcc)
    // pops it into the JLinkedHashMap and 
    // eldest get sent to Output and flush gets new thread to determine
    // if the key needs updating and flush


    val accumulators = new LinkedHashMap[String,String] //Accumulator[_]]

    (capacity, 0.75f, true) {
        override def removeEldestEntry(eldest: JMap.Entry[String, Accumulator[_]]) = {
            if(this.size > capacity) {
                eldest.getValue.write(eldest.getKey, output)
            } else {
                false
            }
        }
    }


    // from UDPInput the key and value are sent to the simmer.update 
    def update(key: String, value: String) {
        // prints update to redis
        println(key)
        println(value)
        // we go get the HLL object here from registry
        val acc = accumulators.get(key)

        // get or update functionality
        // pulls get from the Registry to return the HLL object
        if(acc == null) {
            Registry.get(key) match {
                case Some(agg) => {
                    val newAcc = agg.createAccumulator(value)
                    accumulators.put(key, newAcc)
                }
                case None => error("Could not find aggregator for key " + key)
            }
        } else {
            acc.update(value)
            if(flushEvery.isDefined && acc.count >= flushEvery.get) {
                if(acc.write(key, output)) {
                    accumulators.remove(key)
                }
            }
        }
    }

    def flush {
        //TODO respect the return value from write()
        accumulators.asScala.foreach{case (key,acc) => acc.write(key, output)}
        accumulators.clear
    }
