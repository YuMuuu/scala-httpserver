
class HttpResponse {
  val lineCode = System.lineSeparator
  val headerText = new StringBuilder
  val bodyText = new StringBuilder

  def writeResponse(status: String, contentType: String, body: String): Unit = {
    headerText.append("HTTP/1.1 " + status + lineCode)
    headerText.append("Content-Type: " + contentType + lineCode)
    headerText.append(lineCode)
    bodyText.append(body)
    bodyText.append(lineCode)
  }
}

