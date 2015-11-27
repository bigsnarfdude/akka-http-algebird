
/**
 * Example of T-Digest plus method with Algebird Semigroup
 */

import com.tdunning.math.stats.TDigest
import com.twitter.algebird.{Group, Semigroup}

case object TDigestSemigroup extends Semigroup[TDigest] {
  override def plus(l: TDigest, r: TDigest): TDigest = {
    val td = TDigest.createDigest(math.max(l.compression(), r.compression()))
    td.add(l)
    td.add(r)
    td
  }

  override def sumOption(iter: TraversableOnce[TDigest]): Option[TDigest] = {
    iter.foldLeft(None: Option[TDigest]) {
      case (None, el) =>
        val td = TDigest.createDigest(el.compression())
        td.add(el)
        Some(td)

      case (f@Some(acc), el) =>
        acc.add(el)
        f
    }
  }
}

object DataData {
  val oneSecond = 1000
  val twoMinutes = 2 * 60 * 1000
  val tenMinutes = 10 * 60 * 1000
  val twoHours = 2 * 60 * 60 * 1000

  val mainValues = 10000000
  val badValues = 10000

  //generate 10.000.000 pseudo-random values for normal user session durations
  val goodData = Generator.generate(count = mainValues, from = oneSecond, to = twoMinutes)

  //generate 100.000(1%) pseudo-random values for invalid user session durations
  val badData = Generator.generate(count = badValues, from = tenMinutes, to = twoHours)

  val allData = goodData ++ badData

  val goodDigest = TDigest.createAvlTreeDigest(100)
  val badDigest = TDigest.createAvlTreeDigest(100)
  val allDigest = TDigest.createAvlTreeDigest(100)

  //val goodDigest = TDigest.createTreeDigest(100)
  //val badDigest = TDigest.createTreeDigest(100)
  //val allDigest = TDigest.createTreeDigest(100)


  // add good data values to good digest
  goodData.foreach(value => goodDigest.add(value))

  // add bad data values to bad digest
  badData.foreach(value => badDigest.add(value))

  // add bad data values to bad digest
  allData.foreach(value => allDigest.add(value))

  //this threshold means that we expect ~0.1% of data is anomalies
  val thresholdAllDigest = allDigest.quantile(0.999d).toInt

  val tds = TDigestSemigroup
  val plusDigest = tds.plus(goodDigest, badDigest)

  val thresholdPlusDigest = plusDigest.quantile(0.999d).toInt

  val thresholdGoodDigest = goodDigest.quantile(0.999d).toInt

  val thresholdBadDigest = badDigest.quantile(0.999d).toInt
}