import java.io.{BufferedReader, InputStream, InputStreamReader}
import java.net.Socket

object HttpRequset {

  class HttpRequest {
    //    def toIntOption(s: String): Option[Int] = try {
    //      Some(s.toInt)
    //    } catch {
    //      case _ => None
    //    }

    val CRLF = "\r\n"
    //WSLからcurlを実行しているからなのかこっちでは駄目だった
    val LF = "\n"
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
      val contentLength = headerText.toString().split(LF)
        .filter(_.startsWith("Content-Length"))
        .map(_.split(":")(1).trim)
      //stream文で綺麗に書きたい
      contentLength(0).toInt
    }

    //def getHeaderText(): String = headerText.toString()
    def getHeaderText(): String = {
      textSubString(headerText.toString)
    }

    def getBodyText(): String = {
      textSubString(bodyText.toString())
    }

    def textSubString(str: String): String = {//Some(...)を消すメソッド
      str.substring(5, str.length - 1)
    }



  }

}
