class HttpStatus {
  val lineCode = System.lineSeparator

  def status(s: String): String = {
    s match {
      case "OK"                   => "200 OK"
      case "FORBIDDEN"            => "403 Forbidden"
      case "NOT_FOUND"            => "404 Not Found"
      case "I'M_A_TEAPOT"         => "418 I'm a teapot"
      case "BAD_GATEWAY"          => "502 Bad Gateway"
      case "SERVICE_UNAVAILABLE"  => "503 Service Unavailable"
      case "GATEWAY_TIMEOUT"      => "504 Gateway Timeout"
      case _                      => ""   //エラー理由が分からない時はどんなステータスを吐けばよいのかな？
    }
  }
}
