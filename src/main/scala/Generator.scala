
import java.util.Random

/**
 * Data generator
 */
object Generator {
  /**
   * Creates a stream of pseudo-random values defined by seed.
   * Equal values of seed create equal sequence of random values.
   * Values are defined in the interval [from, to)
   * @param seed seed for random generator
   * @param count count of random values in the stream
   * @param from lower boundary of random value
   * @param to upper boundary of random values.
   * @return a new stream with random values
   */
  def generate(seed: Int = 1, count: Int = 1000000, from: Int, to: Int): Stream[Long] = {
    val rnd = new Random(seed)
    Stream.fill(count)(rndRange(rnd, from, to))
  }

  /**
   * Generate one random value in the defined boundaries
   */
  def rndRange(rnd: Random, from: Int, to: Int): Long = {
    rnd.nextInt(to - from) + from
  }
}
