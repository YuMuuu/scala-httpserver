import java.io._
import java.net._
import HttpRequset.HttpRequest

object Main {

  def main(args: Array[String]): Unit = {
    println("start >>>")

    val server = new ServerSocket(80)
    val socket = server.accept()
    val in = new BufferedReader(new InputStreamReader(socket.getInputStream, "UTF-8"))
    val request = new HttpRequest(in)

    println("<<< end")
  }
}
