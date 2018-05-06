import java.nio.file.{Files, NoSuchFileException, Paths}

class HttpResponseBody {
  val lineCode = System.lineSeparator

  def readBody(s: String): Either[Exception, String] = {
    val readBody = new StringBuilder
    try {
      val path = Paths.get("src/dir" + s)
      val br = Files.newBufferedReader(path)
      Iterator.continually(br.readLine()).takeWhile(_ != null).foreach {
        line => readBody.append(line + lineCode)
      }
      Right(readBody.toString)
    } catch {
      case e: NoSuchFileException => Left(e)
    }
  }
}
