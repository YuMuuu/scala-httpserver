import java.io.{BufferedReader, InputStream, InputStreamReader}

object HttpRequset {

  class HttpRequest {
    val LF = "\n"
    val headerText = new StringBuilder()
    val bodyText = new StringBuilder()


    def HttpRequest(input: InputStream): Unit = {
      val in = new BufferedReader(new InputStreamReader(input, "UTF-8"))

      readHeader(in) match {
        case Some(s) => headerText.append(s)
        case None => headerText.append("can't read header")
      }

      readBody(in) match {
        case Some(s) => bodyText.append(s)
        case None => bodyText.append("can't read body")
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
      val contentLength = getContentLength()
      if (contentLength <= 0) {
        None
      } else {
        val c = new Array[Char](contentLength)
        in.read(c)
        Some(String.valueOf(c))
      }
    }

    def getContentLength(): Integer = {
      val contentLength = headerText.toString().split(LF)
        .filter(_.startsWith("Content-Length"))
        .map(_.split(":")(1).trim)
      //stream文で綺麗に書きたい
      contentLength(0).toInt
    }

    def getHeaderText(): String = {
      headerText.toString()
    }

    def getBodyText(): String = {
      bodyText.toString()
    }
  }

}
