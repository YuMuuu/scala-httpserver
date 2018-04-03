import java.io.{BufferedReader, InputStream, InputStreamReader}

object HttpRequset {

  class HttpRequest {
    val LF = "\n"
    val headerText = new StringBuilder
    val bodyText = new StringBuilder


    def HttpRequest(input: InputStream): Unit = {
      val in = new BufferedReader(new InputStreamReader(input, "UTF-8"))

      readHeader(in) match {
        case Some(s) => headerText.append(s)
        case None => headerText.append("can't read header")
      }

      readBody(in) match {
        case Some(s) => bodyText.append(s)
        case None => bodyText.append("nothing body")
      }
    }

    def readHeader(in: BufferedReader): Option[String] = {
      val line = in.readLine()
      val header = new StringBuilder()

      if (line == null) {
        None
      } else {
        Iterator.continually(in.readLine()).takeWhile(it => it != null && !it.isEmpty).foreach {
          line => header.append(line + "\n")
        }
        Some(header.toString())
      }
    }

    def readBody(in: BufferedReader): Option[String] = {
      if (isChunkedTransfer) {
        Some(readBodyByChunkedTransfer(in))
      } else {
          readBodyByContentLength(in)
      }
      //readBodyByContentLength(in)
    }

    def getContentLength(): Integer = {
      val contentLength = headerText.toString().split(LF)
        .filter(_.startsWith("Content-Length"))
        .map(_.split(":")(1).trim)
      //stream文で綺麗に書きたい
      contentLength(0).toInt
    }

    def isChunkedTransfer(): Boolean = {

      val chunkedTransfer = headerText.toString().split(LF)
        .filter(_.startsWith("Transfer-Encoding"))
        .map(_.split(":")(1).trim)
      chunkedTransfer.length != 0 &&  chunkedTransfer(0) == "chunked"
    }

    def readBodyByChunkedTransfer(in: BufferedReader) = {
      val body = new StringBuilder
      var chunkSize = Integer.parseInt(in.readLine, 16)
      while ( {
        chunkSize != 0
      }) {
        val buffer = new Array[Char](chunkSize)
        in.read(buffer)
        body.append(buffer)
        in.readLine // chunk-body の末尾にある CRLF を読み飛ばす

        chunkSize = Integer.parseInt(in.readLine, 16)
      }
      body.toString
    }

    def readBodyByContentLength(in: BufferedReader): Option[String] = {
      val contentLength = getContentLength()
      if (contentLength <= 0) {
        None
      } else {
        val c = new Array[Char](contentLength)
        in.read(c)
        Some(String.valueOf(c))
      }
    }


  }

}