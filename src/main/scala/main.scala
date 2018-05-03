import java.io._
import java.net._

object Main {
  val lineCode = System.lineSeparator

  def main(args: Array[String]): Unit = {
    println("start >>>")
    val server = new ServerSocket(8080)
    val socket = server.accept

    val in = socket.getInputStream
    val out = new PrintStream(socket.getOutputStream)

    val request = new HttpRequest(in)
    println(request.headerText.toString)
    println(request.bodyText.toString)

    val response = new HttpResponse


    response.headerText.append("HTTP/1.1 200 OK" + lineCode)
    response.headerText.append("Content-Type: text/html" + lineCode)
    response.headerText.append(lineCode)
    response.bodyText.append("<h1>Hello World!!</h1>")
    response.bodyText.append("<p>ざーこざーこ</p>")

    request.getmethod() match{
      case Some(s) => response.bodyText.append(s)
      case None => response.bodyText.append("<h2>nothing getmethod status</h2>")
    }


    out.print(response.headerText.toString)
    out.print(response.bodyText.toString)
    socket.close
    println("<<< end")
  }
}
