package connectors

import baseSpec.BaseSpecWithApplication
import models.User
import org.scalamock.matchers.Matchers
import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import play.api.http.Status
import play.api.libs.json.{JsString, JsValue, Json, OFormat}
import play.api.mvc.Result
import play.api.test.FakeRequest
import play.api.test.Helpers.{contentAsJson, defaultAwaitTimeout, status}

import scala.concurrent.Future

class GithubConnectorSpec extends BaseSpecWithApplication with MockFactory with ScalaFutures with Matchers {

  val parseFileContentsTestString = Json.parse(raw"""{
    "name": "Doctor.java",
    "path": "src/main/java/com/bnta/doctor/Doctor.java",
    "sha": "3b9a015870bbc3ec051b6e6ecbbe2aefdfe9bd0f",
    "size": 1485,
    "url": "https://api.github.com/repos/Jake-Raffe/Back-end_Project/contents/src/main/java/com/bnta/doctor/Doctor.java?ref=main",
    "html_url": "https://github.com/Jake-Raffe/Back-end_Project/blob/main/src/main/java/com/bnta/doctor/Doctor.java",
    "git_url": "https://api.github.com/repos/Jake-Raffe/Back-end_Project/git/blobs/3b9a015870bbc3ec051b6e6ecbbe2aefdfe9bd0f",
    "download_url": "https://raw.githubusercontent.com/Jake-Raffe/Back-end_Project/main/src/main/java/com/bnta/doctor/Doctor.java",
    "type": "file",
    "content": "cGFja2FnZSBjb20uYm50YS5kb2N0b3I7CgppbXBvcnQgamF2YS51dGlsLk9i\namVjdHM7CgpwdWJsaWMgY2xhc3MgRG9jdG9yIHsKICAgICBpbnQgZG9jdG9y\nSWQ7CiAgICAgU3RyaW5nIGRvY3Rvck5hbWU7CiAgICBTdHJpbmcgcm9vbU5h\nbWU7CgogICAgcHVibGljIERvY3RvcihpbnQgZG9jdG9ySWQsIFN0cmluZyBk\nb2N0b3JOYW1lLCBTdHJpbmcgcm9vbU5hbWUpIHsKICAgICAgICB0aGlzLmRv\nY3RvcklkID0gZG9jdG9ySWQ7CiAgICAgICAgdGhpcy5kb2N0b3JOYW1lID0g\nZG9jdG9yTmFtZTsKICAgICAgICB0aGlzLnJvb21OYW1lID0gcm9vbU5hbWU7\nCiAgICB9CgogICAgcHVibGljIGludCBnZXREb2N0b3JJZCgpIHsKICAgICAg\nICByZXR1cm4gZG9jdG9ySWQ7CiAgICB9CgogICAgcHVibGljIHZvaWQgc2V0\nRG9jdG9ySWQoaW50IGRvY3RvcklkKSB7CiAgICAgICAgdGhpcy5kb2N0b3JJ\nZCA9IGRvY3RvcklkOwogICAgfQoKICAgIHB1YmxpYyBTdHJpbmcgZ2V0RG9j\ndG9yTmFtZSgpIHsKICAgICAgICByZXR1cm4gZG9jdG9yTmFtZTsKICAgIH0K\nCiAgICBwdWJsaWMgdm9pZCBzZXREb2N0b3JOYW1lKFN0cmluZyBkb2N0b3JO\nYW1lKSB7CiAgICAgICAgdGhpcy5kb2N0b3JOYW1lID0gZG9jdG9yTmFtZTsK\nICAgIH0KCiAgICBwdWJsaWMgU3RyaW5nIGdldFJvb21OYW1lKCkgewogICAg\nICAgIHJldHVybiByb29tTmFtZTsKICAgIH0KCiAgICBwdWJsaWMgdm9pZCBz\nZXRSb29tTmFtZShTdHJpbmcgcm9vbU5hbWUpIHsKICAgICAgICB0aGlzLnJv\nb21OYW1lID0gcm9vbU5hbWU7CiAgICB9CgogICAgQE92ZXJyaWRlCiAgICBw\ndWJsaWMgU3RyaW5nIHRvU3RyaW5nKCkgewogICAgICAgIHJldHVybiAiRG9j\ndG9yeyIgKwogICAgICAgICAgICAgICAgImRvY3RvcklkPSIgKyBkb2N0b3JJ\nZCArCiAgICAgICAgICAgICAgICAiLCBkb2N0b3JOYW1lPSciICsgZG9jdG9y\nTmFtZSArICdcJycgKwogICAgICAgICAgICAgICAgIiwgcm9vbU5hbWU9JyIg\nKyByb29tTmFtZSArICdcJycgKwogICAgICAgICAgICAgICAgJ30nOwogICAg\nfQoKICAgIEBPdmVycmlkZQogICAgcHVibGljIGJvb2xlYW4gZXF1YWxzKE9i\namVjdCBvKSB7CiAgICAgICAgaWYgKHRoaXMgPT0gbykgcmV0dXJuIHRydWU7\nCiAgICAgICAgaWYgKG8gPT0gbnVsbCB8fCBnZXRDbGFzcygpICE9IG8uZ2V0\nQ2xhc3MoKSkgcmV0dXJuIGZhbHNlOwogICAgICAgIERvY3RvciBkb2N0b3Ig\nPSAoRG9jdG9yKSBvOwogICAgICAgIHJldHVybiBkb2N0b3JJZCA9PSBkb2N0\nb3IuZG9jdG9ySWQgJiYgT2JqZWN0cy5lcXVhbHMoZG9jdG9yTmFtZSwgZG9j\ndG9yLmRvY3Rvck5hbWUpICYmIE9iamVjdHMuZXF1YWxzKHJvb21OYW1lLCBk\nb2N0b3Iucm9vbU5hbWUpOwogICAgfQoKICAgIEBPdmVycmlkZQogICAgcHVi\nbGljIGludCBoYXNoQ29kZSgpIHsKICAgICAgICByZXR1cm4gT2JqZWN0cy5o\nYXNoKGRvY3RvcklkLCBkb2N0b3JOYW1lLCByb29tTmFtZSk7CiAgICB9Cn0K\n",
    "encoding": "base64",
    "_links": {
      "self": "https://api.github.com/repos/Jake-Raffe/Back-end_Project/contents/src/main/java/com/bnta/doctor/Doctor.java?ref=main",
      "git": "https://api.github.com/repos/Jake-Raffe/Back-end_Project/git/blobs/3b9a015870bbc3ec051b6e6ecbbe2aefdfe9bd0f",
      "html": "https://github.com/Jake-Raffe/Back-end_Project/blob/main/src/main/java/com/bnta/doctor/Doctor.java"
    }
  }""")
  val githubConnector = new GithubConnector(ws)

  val getUserUrl = "https://api.github.com/users/jake-raffe"
  val jakeUser = new User("Jake-Raffe", "2022-01-07T19:56:27Z", None, 4,5)
  val url = s"https://api.github.com/repos/Jake-Raffe/scala_cafe-project/contents/src/fileNameHere.scala"


//  "GithubConnector .getUser" should {
//    "get a github user by their login name and return their details in a User object" in {
//      val result = githubConnector.getUser(getUserUrl)
//      result shouldBe Right(jakeUser)
//    }
//  }

  "GithubConnector .parseFileContents" should {
    "read the base64 contents of a file from the Github API and return as a string" in {
      val contentString = GithubConnector.parseFileContents(parseFileContentsTestString)
      contentString shouldBe "cGFja2FnZSBjb20uYm50YS5kb2N0b3I7CgppbXBvcnQgamF2YS51dGlsLk9i\namVjdHM7CgpwdWJsaWMgY2xhc3MgRG9jdG9yIHsKICAgICBpbnQgZG9jdG9y\nSWQ7CiAgICAgU3RyaW5nIGRvY3Rvck5hbWU7CiAgICBTdHJpbmcgcm9vbU5h\nbWU7CgogICAgcHVibGljIERvY3RvcihpbnQgZG9jdG9ySWQsIFN0cmluZyBk\nb2N0b3JOYW1lLCBTdHJpbmcgcm9vbU5hbWUpIHsKICAgICAgICB0aGlzLmRv\nY3RvcklkID0gZG9jdG9ySWQ7CiAgICAgICAgdGhpcy5kb2N0b3JOYW1lID0g\nZG9jdG9yTmFtZTsKICAgICAgICB0aGlzLnJvb21OYW1lID0gcm9vbU5hbWU7\nCiAgICB9CgogICAgcHVibGljIGludCBnZXREb2N0b3JJZCgpIHsKICAgICAg\nICByZXR1cm4gZG9jdG9ySWQ7CiAgICB9CgogICAgcHVibGljIHZvaWQgc2V0\nRG9jdG9ySWQoaW50IGRvY3RvcklkKSB7CiAgICAgICAgdGhpcy5kb2N0b3JJ\nZCA9IGRvY3RvcklkOwogICAgfQoKICAgIHB1YmxpYyBTdHJpbmcgZ2V0RG9j\ndG9yTmFtZSgpIHsKICAgICAgICByZXR1cm4gZG9jdG9yTmFtZTsKICAgIH0K\nCiAgICBwdWJsaWMgdm9pZCBzZXREb2N0b3JOYW1lKFN0cmluZyBkb2N0b3JO\nYW1lKSB7CiAgICAgICAgdGhpcy5kb2N0b3JOYW1lID0gZG9jdG9yTmFtZTsK\nICAgIH0KCiAgICBwdWJsaWMgU3RyaW5nIGdldFJvb21OYW1lKCkgewogICAg\nICAgIHJldHVybiByb29tTmFtZTsKICAgIH0KCiAgICBwdWJsaWMgdm9pZCBz\nZXRSb29tTmFtZShTdHJpbmcgcm9vbU5hbWUpIHsKICAgICAgICB0aGlzLnJv\nb21OYW1lID0gcm9vbU5hbWU7CiAgICB9CgogICAgQE92ZXJyaWRlCiAgICBw\ndWJsaWMgU3RyaW5nIHRvU3RyaW5nKCkgewogICAgICAgIHJldHVybiAiRG9j\ndG9yeyIgKwogICAgICAgICAgICAgICAgImRvY3RvcklkPSIgKyBkb2N0b3JJ\nZCArCiAgICAgICAgICAgICAgICAiLCBkb2N0b3JOYW1lPSciICsgZG9jdG9y\nTmFtZSArICdcJycgKwogICAgICAgICAgICAgICAgIiwgcm9vbU5hbWU9JyIg\nKyByb29tTmFtZSArICdcJycgKwogICAgICAgICAgICAgICAgJ30nOwogICAg\nfQoKICAgIEBPdmVycmlkZQogICAgcHVibGljIGJvb2xlYW4gZXF1YWxzKE9i\namVjdCBvKSB7CiAgICAgICAgaWYgKHRoaXMgPT0gbykgcmV0dXJuIHRydWU7\nCiAgICAgICAgaWYgKG8gPT0gbnVsbCB8fCBnZXRDbGFzcygpICE9IG8uZ2V0\nQ2xhc3MoKSkgcmV0dXJuIGZhbHNlOwogICAgICAgIERvY3RvciBkb2N0b3Ig\nPSAoRG9jdG9yKSBvOwogICAgICAgIHJldHVybiBkb2N0b3JJZCA9PSBkb2N0\nb3IuZG9jdG9ySWQgJiYgT2JqZWN0cy5lcXVhbHMoZG9jdG9yTmFtZSwgZG9j\ndG9yLmRvY3Rvck5hbWUpICYmIE9iamVjdHMuZXF1YWxzKHJvb21OYW1lLCBk\nb2N0b3Iucm9vbU5hbWUpOwogICAgfQoKICAgIEBPdmVycmlkZQogICAgcHVi\nbGljIGludCBoYXNoQ29kZSgpIHsKICAgICAgICByZXR1cm4gT2JqZWN0cy5o\nYXNoKGRvY3RvcklkLCBkb2N0b3JOYW1lLCByb29tTmFtZSk7CiAgICB9Cn0K\n"
    }
  }

  "GithubConnector .createNewFile" should {
    "connect to the api to update the repository - adding the new file to the path location" in {

    }
  }

}
