package org.ditw

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}
import scala.reflect.runtime.universe._

class PlaceHolderTests extends FlatSpec with Matchers with TableDrivenPropertyChecks {


  private val testData = Table(
    ("name", "output"),
    ("John", "Hello John!"),
    ("Mr. Smith", "Hello Mr. Smith!")
  )

  private def typeTagTest[T:TypeTag](t:T):Unit = {
    println(implicitly[TypeTag[T]].toString())
  }

  "sayHi" should "output 'Hello [name]!'" in {
    forAll (testData) { (name, output) =>
      val hi = PlaceHolder.sayHi(name)
      hi shouldBe output
    }
    typeTagTest(List("new"))

  }

}