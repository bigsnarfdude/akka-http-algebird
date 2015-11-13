
// case classes marshalled by json4s package instead of spray implicits
case class PageRecords(totalCount: Int, page: Int, transactions: List[Record])

case class Record(Date: String, Ledger: String, Amount: String, Company: String)

case class NormalizedRecord(Date: java.util.Date, Ledger: String, Amount: Double, Company: String)

case class Result(sum: Double)

case class Page(page: String)

case class AddHLL(key: String, value: String)