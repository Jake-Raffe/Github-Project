package services

import baseSpec.BaseSpecWithApplication
import connectors.GithubConnector
import models.User
import models.User.formats
import org.scalamock.scalatest.MockFactory
import play.api.libs.json.Json
import play.api.mvc.Results.Ok

import scala.concurrent.Future

class GithubServiceUnitSpec extends BaseSpecWithApplication with MockFactory {

  val mockGithubConnector = mock[GithubConnector]
  val testGithubService = new GithubService(mockGithubConnector)


  val jakeUser = new User("Jake-Raffe", "2022-01-07T19:56:27Z", None, 4,5)
  val encodedString = "cGFja2FnZSBjb20uYm50YS5kb2N0b3I7CgppbXBvcnQgamF2YS51dGlsLk9i\namVjdHM7CgpwdWJsaWMgY2xhc3MgRG9jdG9yIHsKICAgICBpbnQgZG9jdG9y\nSWQ7CiAgICAgU3RyaW5nIGRvY3Rvck5hbWU7CiAgICBTdHJpbmcgcm9vbU5h\nbWU7CgogICAgcHVibGljIERvY3RvcihpbnQgZG9jdG9ySWQsIFN0cmluZyBk\nb2N0b3JOYW1lLCBTdHJpbmcgcm9vbU5hbWUpIHsKICAgICAgICB0aGlzLmRv\nY3RvcklkID0gZG9jdG9ySWQ7CiAgICAgICAgdGhpcy5kb2N0b3JOYW1lID0g\nZG9jdG9yTmFtZTsKICAgICAgICB0aGlzLnJvb21OYW1lID0gcm9vbU5hbWU7\nCiAgICB9CgogICAgcHVibGljIGludCBnZXREb2N0b3JJZCgpIHsKICAgICAg\nICByZXR1cm4gZG9jdG9ySWQ7CiAgICB9CgogICAgcHVibGljIHZvaWQgc2V0\nRG9jdG9ySWQoaW50IGRvY3RvcklkKSB7CiAgICAgICAgdGhpcy5kb2N0b3JJ\nZCA9IGRvY3RvcklkOwogICAgfQoKICAgIHB1YmxpYyBTdHJpbmcgZ2V0RG9j\ndG9yTmFtZSgpIHsKICAgICAgICByZXR1cm4gZG9jdG9yTmFtZTsKICAgIH0K\nCiAgICBwdWJsaWMgdm9pZCBzZXREb2N0b3JOYW1lKFN0cmluZyBkb2N0b3JO\nYW1lKSB7CiAgICAgICAgdGhpcy5kb2N0b3JOYW1lID0gZG9jdG9yTmFtZTsK\nICAgIH0KCiAgICBwdWJsaWMgU3RyaW5nIGdldFJvb21OYW1lKCkgewogICAg\nICAgIHJldHVybiByb29tTmFtZTsKICAgIH0KCiAgICBwdWJsaWMgdm9pZCBz\nZXRSb29tTmFtZShTdHJpbmcgcm9vbU5hbWUpIHsKICAgICAgICB0aGlzLnJv\nb21OYW1lID0gcm9vbU5hbWU7CiAgICB9CgogICAgQE92ZXJyaWRlCiAgICBw\ndWJsaWMgU3RyaW5nIHRvU3RyaW5nKCkgewogICAgICAgIHJldHVybiAiRG9j\ndG9yeyIgKwogICAgICAgICAgICAgICAgImRvY3RvcklkPSIgKyBkb2N0b3JJ\nZCArCiAgICAgICAgICAgICAgICAiLCBkb2N0b3JOYW1lPSciICsgZG9jdG9y\nTmFtZSArICdcJycgKwogICAgICAgICAgICAgICAgIiwgcm9vbU5hbWU9JyIg\nKyByb29tTmFtZSArICdcJycgKwogICAgICAgICAgICAgICAgJ30nOwogICAg\nfQoKICAgIEBPdmVycmlkZQogICAgcHVibGljIGJvb2xlYW4gZXF1YWxzKE9i\namVjdCBvKSB7CiAgICAgICAgaWYgKHRoaXMgPT0gbykgcmV0dXJuIHRydWU7\nCiAgICAgICAgaWYgKG8gPT0gbnVsbCB8fCBnZXRDbGFzcygpICE9IG8uZ2V0\nQ2xhc3MoKSkgcmV0dXJuIGZhbHNlOwogICAgICAgIERvY3RvciBkb2N0b3Ig\nPSAoRG9jdG9yKSBvOwogICAgICAgIHJldHVybiBkb2N0b3JJZCA9PSBkb2N0\nb3IuZG9jdG9ySWQgJiYgT2JqZWN0cy5lcXVhbHMoZG9jdG9yTmFtZSwgZG9j\ndG9yLmRvY3Rvck5hbWUpICYmIE9iamVjdHMuZXF1YWxzKHJvb21OYW1lLCBk\nb2N0b3Iucm9vbU5hbWUpOwogICAgfQoKICAgIEBPdmVycmlkZQogICAgcHVi\nbGljIGludCBoYXNoQ29kZSgpIHsKICAgICAgICByZXR1cm4gT2JqZWN0cy5o\nYXNoKGRvY3RvcklkLCBkb2N0b3JOYW1lLCByb29tTmFtZSk7CiAgICB9Cn0K\n"
  val fileStartsWith = "package com.bnta.doctor;"

  "GithubService .decodeFromBase64" should {
    "convert a base64 string to a decoded plain text string" in {
      GithubService.decodeBase64(encodedString).startsWith(fileStartsWith) shouldEqual true
    }
  }

  "Github .getUser" should {
    "receive a user from the connector that corresponds to their github login" in {
      (mockGithubConnector.getUser(_: String))
        .expects("https://api.github.com/users/jake-raffe")
        .returning(Future(Right(jakeUser)))
        .once()
      val result = testGithubService.getUser("jake-raffe")
      result.shouldBe(Future(Right(jakeUser)))
    }
  }

}
