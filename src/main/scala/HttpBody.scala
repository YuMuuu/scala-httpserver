import java.io.{BufferedReader, InputStream}

class HttpBody(input: InputStream, header: HttpHeader) {
  def readBody(in: BufferedReader, h: HttpHeader = header): Option[String] = {
    if (h.isChunkedTransfer) Some(readBodyByChunkedTransfer(in))
    else readBodyByContentLength(in)
  }

  def readBodyByChunkedTransfer(in: BufferedReader): String = {
    val body = new StringBuilder
    Iterator.continually(Integer.parseInt(in.readLine, 16)).takeWhile(_ != 0).foreach {
      line =>
        val buffer = new Array[Char](line)
        in.read(buffer)
        body.append(String.valueOf(buffer))
        in.readLine
    }
    body.toString
  }

  def readBodyByContentLength(in: BufferedReader, h: HttpHeader = header): Option[String] = {
    val contentLength = h.getContentLength()
    if (contentLength <= 0) None
    else {
      val c = new Array[Char](contentLength)
      in.read(c)
      Some(String.valueOf(c))
    }
  }
}
