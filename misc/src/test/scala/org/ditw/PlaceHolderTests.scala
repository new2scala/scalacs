package org.ditw

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class PlaceHolderTests extends FlatSpec with Matchers with TableDrivenPropertyChecks {

  private val testData = Table(
    ("name", "output"),
    ("John", "Hello John!"),
    ("Mr. Smith", "Hello Mr. Smith!")
  )

  "sayHi" should "output 'Hello [name]!'" in {
    forAll (testData) { (name, output) =>
      val hi = PlaceHolder.sayHi(name)
      hi shouldBe output
    }
  }

}