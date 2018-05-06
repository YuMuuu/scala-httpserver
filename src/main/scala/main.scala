import java.io._
import java.net._
import java.nio.file.{Files, Paths}

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
    val status = new HttpStatus
    //リクエストがhtmlだったら


    request.getmethod() match {
      case Some(s) => {
        val body = new HttpResponseBody
        //response.bodyText.append(body.readBody(s))
        body.readBody(s) match {
          case Right(body) => {
            //正常な場合
            response.headerText.append("HTTP/1.1 200 OK" + status.status("OK") + lineCode)
            response.headerText.append("Content-Type: text/html" + lineCode)
            response.headerText.append(lineCode)
            response.bodyText.append(body)
          }
          case Left(e) => {
            //存在しないファイルを指定した時
            response.headerText.append("HTTP/1.1 " + status.status("NOT_FOUND") + lineCode)
            response.headerText.append("Content-Type: text/html" + lineCode)
            response.headerText.append(lineCode)
            response.headerText.append(status.status("NOT_FOUND"))
          }
        }
      }
      case None => {
        //カレントディレクトリは見せねぇ！
        response.headerText.append("HTTP/1.1 " + status.status("FORBIDDEN") + lineCode)
        response.headerText.append("Content-Type: text/html" + lineCode)
        response.headerText.append(lineCode)
        response.headerText.append(status.status("FORBIDDEN"))
      }
    }
    out.print(response.headerText.toString)
    out.print(response.bodyText.toString)
    socket.close
    println("<<< end")
  }
}
