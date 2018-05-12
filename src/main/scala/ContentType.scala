class ContentType {
  contentType("txt")
  def contentType(s: String): String = {
    s match {
      case "txt" => "text/plain"
      case "html" => "text/html"
      case "css" => "text/css"
      case "js" => "application/javascript"
      case "jpg" => "image/jpg"
      case "jpeg" => "image/jpg"
      case "png" => "image/png"
      case "gif" => "image/gif"
      case _ => "text/plain" //不明な形ならばplainで
    }
  }
}
