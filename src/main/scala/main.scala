import java.io._
import java.net._

object Main {
  val CRLF = "\r\n"

  def main(args: Array[String]): Unit = {
    println("start >>>")
    val server = new ServerSocket(8080)
    val socket = server.accept
    val in = socket.getInputStream
    val out = new PrintStream(socket.getOutputStream)
    val request = new HttpRequest(in)

    println(request.headerText.toString)
    println(request.bodyText.toString)

    //out.println(request.headerText.toString())
    //out.println(request.bodyText.toString())
    //out.println("HTT&P/1.1 200 OK" )
    out.println("Content-Type: text/html")
    out.println("firefoxだと<pre></pre>で表示される")
    out.println("chromeだと無効な応答だと怒られて接続できない")
    out.println("IEだとfirefox+文字化けになる")
    out.println("どうすればいいんだろう")
    socket.close
    println("<<< end")


  }
}
