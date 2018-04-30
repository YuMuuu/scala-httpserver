import java.io.{BufferedReader, InputStream, InputStreamReader}

class HttpHeader(input: InputStream, headerText:StringBuilder) {
  val LF = "\r\n"
  val in = new BufferedReader(new InputStreamReader(input, "UTF-8"))

  def readHeader(in: BufferedReader): Option[String] = {
    val line = in.readLine //読み捨て
    val header = new StringBuilder

    if (line == null) None
    else {
      Iterator.continually(in.readLine()).takeWhile(it => it != null && !it.isEmpty).foreach {
        line => header.append(line + LF)
      }
      Some(header.toString)
    }
  }

  def isChunkedTransfer(): Boolean = {
    val chunkedTransfer = headerText.toString().split(LF)
      .filter(_.startsWith("Transfer-Encoding"))
      .map(_.split(":")(1).trim)
    chunkedTransfer.length > 0 && chunkedTransfer(0) == "chunked"
  }

  def getContentLength(): Integer = {
    val contentLength = headerText.toString().split(LF)
      .filter(_.startsWith("Content-Length"))
      .map(_.split(":")(1).trim)
    //stream文で綺麗に書きたい
    if (contentLength.length > 0) contentLength(0).toInt
    else 0
  }

}

