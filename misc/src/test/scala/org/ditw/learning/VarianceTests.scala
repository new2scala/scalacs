package org.ditw.learning

import scala.collection.mutable.ListBuffer

object VarianceTests extends App {
  val lb = ListBuffer[String]()

  def f1(lb:ListBuffer[Any]):Unit = {
    println(lb.size)
  }

  val l = List[String]()
  def f2(l:List[Any]):Unit = {
    println(l.size)
  }

  def f3(it:Iterable[Any]):Unit = {
    println(it.size)
  }

  //do not compile: f1(lb)
  f2(l)
  f3(lb)
  f3(l)

  import collection.mutable

  val ms = mutable.IndexedSeq[String]()

  def f4(ms:mutable.IndexedSeq[Any]):Unit = {
    println(ms.size)
  }
  def f5(s:IndexedSeq[Any]):Unit = {
    println(s.size)
  }

  //do not compile: f4(ms)

  val s = IndexedSeq[String]()
  f5(s)
}
