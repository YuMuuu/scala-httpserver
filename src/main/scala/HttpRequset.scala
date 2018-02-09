import java.io.{BufferedReader, InputStream, InputStreamReader}
//import java.io_
//import java.net_

object HttpRequset {

 class HttpRequest{
   val CRLF = "\r\n"
   var hedderText = ""
   var bodyText = ""

   def HttpReqest(input: InputStream):Unit = {
     val in = new BufferedReader(new InputStreamReader(input, "UTF-8"))
   }
   def readHedder(in: BufferReader):Unit ={
     val line = in.readLine()
     val header = new StringBuilder()
     var contentLength = 0

     Iterator.continually(in.readLine()).takeWhile(it => it != null && !it.isEmpty).foreach {
       line =>
         header.append(line + "\n")
     }
   }
 }
}
