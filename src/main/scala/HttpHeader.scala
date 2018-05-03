import java.io.{BufferedReader, InputStream, InputStreamReader}

class HttpHeader(input: InputStream, headerText: StringBuilder) {
  val lineCode = System.lineSeparator
  val in = new BufferedReader(new InputStreamReader(input, "UTF-8"))


  def readHeader(in: BufferedReader): Option[String] = {
    val header = new StringBuilder

    Iterator.continually(in.readLine()).takeWhile(it => it != null && !it.isEmpty).foreach {
      line => header.append(line + lineCode)
    }
    Some(header.toString)
  }


  def isChunkedTransfer(): Boolean = {
    val chunkedTransfer = headerText.toString.split(lineCode)
      .filter(_.startsWith("Transfer-Encoding"))
      .map(_.split(":")(1).trim)
    chunkedTransfer.length > 0 && chunkedTransfer(0) == "chunked"
  }

  def getContentLength(): Integer = {
    val contentLength = headerText.toString.split(lineCode)
      .filter(_.startsWith("Content-Length"))
      .map(_.split(":")(1).trim)
    //綺麗に書きたい
    if (contentLength.length > 0) contentLength(0).toInt
    else 0
  }
}

