import java.io._
import java.net._

object Main {
  def main(args: Array[String]): Unit = {
    println("start >>>")
    //val server = new ServerSocket(8080)
    //val socket = server.accept
    //val in = socket.getInputStream
    //どっちの方が綺麗かな？
    val in = new ServerSocket(8080).accept.getInputStream
    val request = new HttpRequest(in)

    println(request.headerText.toString)
    println(request.bodyText.toString)

    println("<<< end")
  }
}
