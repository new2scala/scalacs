package org.ditw.learning

object AuxTest extends App {

  trait Tr[A] {
    type B
    val data:B
  }

  object Tr {
    type Aux[A, B1] = Tr[A] { type B = B1 }
    def genTr[TB](d:TB) :Tr[Int] = {
      new Tr[Int] {
        type B = TB
        val data = d
      }
    }

    def f[A](tr:Tr[A]):tr.B = {
      println(tr.data)
      tr.data
    }

    val intStr1 = genTr("abc")
    println(intStr1.data)

    val intBool1 = genTr(true)
    println(intBool1.data)

    f(intStr1)
    f(intBool1)

    implicit val intStrAux = new Tr[Int] {
      type B = String
      val data:String = "NA"
    }

    def f2[A, B](tr:Tr[A], d:B)(implicit aux:Tr.Aux[A, B]):Boolean = {
      val eq = tr.data == d
      println(eq)
      eq
    }

  }

  import Tr._
  val intStr2 = genTr("def")
  f2(intStr1, "bc")
  //do not compile: f2(intStr1, true)
}
