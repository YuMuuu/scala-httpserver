import java.io._
import java.net._

import scala.collection.JavaConverters._

object Main {


  def safeStringToInt(str: String): Option[Int] = {
    import scala.util.control.Exception._
    catching(classOf[NumberFormatException]) opt str.toInt
  }


  def main(args: Array[String]): Unit = {
    println("start >>>")

    val server = new ServerSocket(80)
    val socket = server.accept()
    val in = new BufferedReader(new InputStreamReader(socket.getInputStream, "UTF-8"))

    val header = new StringBuilder()
    val body = new StringBuilder()

    var contentLength = 0

    Iterator.continually(in.readLine()).takeWhile(it => it != null && !it.isEmpty).foreach {
      line =>
        header.append(line + "\n");
        if (line.startsWith("Content-Length")) {
          contentLength = line.split(":")(1).trim.toInt
        }
    }

    if (0 < contentLength) {
      val c = new Array[Char](contentLength)
      in.read(c)
      body.append(String.valueOf(c))
    }


    println(header)
    println(body)
    println()
    println("<<< end")
  }
}
