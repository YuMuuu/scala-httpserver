import java.io._
import java.net._
import HttpRequset.HttpRequest

object Main {

  def main(args: Array[String]): Unit = {
    println("start >>>")
    val server = new ServerSocket(80)
    val socket = server.accept
    val in: InputStream = socket.getInputStream
    val request = new HttpRequest {
      HttpRequest(in)
    }
    println(request.getHeaderText)
    println(request.getBodyText)
    println("<<< end")
  }

}
