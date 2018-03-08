import java.io.{BufferedReader, InputStream, InputStreamReader}

import scala.collection.immutable.Stream

object HttpRequset {

  class HttpRequest {
    val CRLF = "\r\n"
    val headerText = new StringBuilder()
    val bodyText = new StringBuilder()

    def HttpRequest(input: InputStream): Unit = {
      val in = new BufferedReader(new InputStreamReader(input, "UTF-8"))
      headerText.append(readHeader(in))
      bodyText.append(readBody(in))

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
//      Stream.of(headerText.split(CRLF))
//        .filter(_.startsWith("Content-Length"))
//        .map(_.split(":")(1).trim)
//        .m apToInt (Integer.parseInt)
//        .findFirst.orElse(0)


    }

    def getHeaderText(): String = {
      headerText.toString()
    }

    def getBodyText(): String = {
      bodyText.toString()
    }


  }

}
