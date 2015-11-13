
// case classes marshalled by json4s package instead of spray implicits
case class Result(sum: Double)

case class Page(page: String)

case class AddHLL(key: String, value: String)