import java.io.{BufferedReader, InputStream, InputStreamReader}
//import java.io_
//import java.net_

object HttpRequset {

 class HttpRequest{
   val CRLF = "\r\n"
   val hedderText = ""
   val bodyText = ""

   def HttpReqest(input: InputStream):Unit = {
     val in = new BufferedReader(new InputStreamReade r(input, "UTF-8"))
   }

   def readHedder(in: BufferedReader):Unit ={
     val line = in.readLine()
     val header = new StringBuilder()
     var contentLength = 0

     Iterator.continually(in.readLine()).takeWhile(it => it != null && !it.isEmpty).foreach {
       line =>
         header.append(line + "\n")
     }
     header.toString();
   }


 }
}
