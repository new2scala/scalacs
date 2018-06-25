package org.ditw.learning

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class NothingTests extends FlatSpec with Matchers with TableDrivenPropertyChecks {
  //class NothingCannotBeInherited extends Nothing
  //  therefore 'println(new NothingCannotBeInherited)' does not compile

  "Nil is a List[Nothing], and Nothing is 'subtype of everything' test" should "pass" in {
    val intList:List[Int] = 1 :: 2 :: Nil
    //println(intList)
    intList.size shouldBe 2

    val strList:List[String] = "let" :: "us" :: Nil
    strList.size shouldBe 2

    val nothingList:List[Nothing] = List()
    val strListNothing:List[String] = nothingList // compiles since Nothing <: String
    println(strListNothing)
    strListNothing shouldBe Nil
  }

  "None is an Option[Nothing] test" should "pass" in {
    val optNothing:Option[Nothing] = None
    println(optNothing)

    val optInt:Option[Int] = Option(12)
    println(optInt)

    val optIntNothing:Option[Int] = optNothing
    optIntNothing shouldBe None

    val optStr:Option[String] = Option("abc")
    println(optStr)

    val optStrNothing:Option[String] = optNothing
    optStrNothing shouldBe None
  }

  def alwaysThrows(a:Any):Nothing = {
    throw new RuntimeException(s"throwing $a")
  }

  "Nothing can be used as the return type tests" should "pass" in {
    val c1 = intercept[RuntimeException] {
      println("throwing int")
      alwaysThrows(1)
    }
    c1.getMessage shouldBe "throwing 1"
    val c2 = intercept[RuntimeException] {
      println("throwing string")
      alwaysThrows("abc")
    }
    c2.getMessage shouldBe "throwing abc"
  }
}
