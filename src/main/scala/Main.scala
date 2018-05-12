import java.io._
import java.net._
import java.nio.file.{Files, Paths}
import java.util.concurrent.{Executor, Executors}
import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress
import com.sun.net.httpserver.{HttpContext, HttpExchange, HttpHandler, HttpServer}


object Main {
  //val lineCode = System.lineSeparator

  def main(args: Array[String]): Unit = {
    println("start >>>")
    val httpserver = new ScalaHttpServer()
    println("<<< end")
  }
}
