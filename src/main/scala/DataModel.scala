
// case classes marshalled by json4s package instead of spray implicits
case class PageRecords(totalCount: Int, page: Int, transactions: List[Record])

case class Record(Date: String, Ledger: String, Amount: String, Company: String)

case class NormalizedRecord(Date: java.util.Date, Ledger: String, Amount: Double, Company: String)

case class Result(estimatedSize: Double)

case class Page(page: String)

case class AddHLL(key: String, value: String)

case class HLLResult(key: String, estimatedSize: Double, hllString: String)

case class GetHyperLogLog(servername: String, count: String, interval: String, timestamp: String)

case class EstimatedSize(servername: String, interval: String, estimatedSize: Double)