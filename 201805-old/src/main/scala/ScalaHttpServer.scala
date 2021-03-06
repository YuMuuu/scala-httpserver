import java.io.PrintStream
import java.net._
import java.util.concurrent.TimeUnit

class ScalaHttpServer {
  val lineCode = System.lineSeparator
  val server = new ServerSocket(8080)
  while (true) {
    run(server)
  }

  def run(server: ServerSocket) {
    val socket = server.accept

    val in = socket.getInputStream
    val out = new PrintStream(socket.getOutputStream)

    val request = new HttpRequest(in)
    println(request.headerText.toString)
    println(request.bodyText.toString)

    val response = new HttpResponse
    val status = new HttpStatus
    val contentType = new ContentType

    request.getmethod() match {
      case Some(s) => {
        val body = new HttpResponseBody
        val filename = body.getFileName(s)
        body.readBody(s) match {
          case Right(responseBody) => response.writeResponse(status.status("OK"), contentType.contentType(filename), responseBody) //正常な場合
          case Left(e) => response.writeResponse(status.status("NOT_FOUND"), contentType.contentType("txt"), status.status("NOT_FOUND")) //存在しないファイルを指定した時
        }
      }
      case None => {
        response.writeResponse(status.status("FORBIDDEN"), contentType.contentType("txt"), status.status("FORBIDDEN")) //カレントディレクトリは見せねぇ！
      }
    }
    out.print(response.headerText.toString)
    out.print(response.bodyText.toString)
    out.close
  }
}
