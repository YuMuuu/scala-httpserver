import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, NoSuchFileException, Path, Paths}

class HttpResponseBody {
  val lineCode = System.lineSeparator

  def readBody(s: String): Either[Exception, String] = {
    val readBody = new StringBuilder
    try {
      val path = Paths.get("src/dir" + s)
//      val br = Files.newBufferedReader(path, StandardCharsets.UTF_8)
//      Iterator.continually(br.readLine()).takeWhile(_ != null).foreach {
//        line => readBody.append(line + lineCode)
//      }
      val bytes = Files.readAllBytes(path)
      readBody.append(StandardCharsets.UTF_8.decode(ByteBuffer.wrap(bytes)).toString())
      //readBody.append(bytes.toString)
        Right(readBody.toString)
    } catch {
      case e: NoSuchFileException => Left(e)
    }
  }

  def getFileName(s: String): String = {
    val path = Paths.get("src/dir" + s)
    //if (Files.exists(path)) { //これが謎にfalseになる
    val filename = path.getFileName.toString.split('.')
    filename(1)
    //}
    //"はげ"//何も無いときは空文字を返す
  }
}
