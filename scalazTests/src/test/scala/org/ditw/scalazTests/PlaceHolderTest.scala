package org.ditw.scalazTests

import scala.reflect.runtime.universe._

object PlaceHolderTest extends App {
  import scalaz._
  import Scalaz._

  println(Monoid[Int].zero)

  def t1[T:TypeTag](t:T):Unit = {
    println(implicitly[TypeTag[T]].toString())
  }

  t1(123)
}
