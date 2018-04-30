import java.io.{BufferedReader, InputStream, InputStreamReader}

class HttpRequest(input: InputStream) {
  val headerText = new StringBuilder
  val bodyText = new StringBuilder
  val in = new BufferedReader(new InputStreamReader(input, "UTF-8"))

  val header = new HttpHeader(input, headerText)
  val body = new HttpBody(input, header)

  header.readHeader(in) match {
    case Some(s) => headerText.append(s)
    case None => headerText.append("can't read header")
  }
  body.readBody(in) match {
    case Some(s) => bodyText.append(s)
    case None => bodyText.append("nothing body")
  }
}