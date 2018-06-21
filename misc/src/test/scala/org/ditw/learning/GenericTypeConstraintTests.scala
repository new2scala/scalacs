package org.ditw.learning

import scala.reflect.ClassTag

object GenericTypeConstraintTests extends App {
  def checkPair[S](p1:S, p2:S):(S, S) = p1 -> p2

  println(
    checkPair(1, "test1")
  )
  println(
    checkPair(23, 23L)
  )

  def checkPair2[S : ClassTag](p1:S, p2:S):(S, S) = {
    println(implicitly[ClassTag[S]])
    p1 -> p2
  }
  println(
    checkPair2(1, "test1")
  )
  println(
    checkPair2(23, 23L)
  )

  def checkPair3[S, T](p1:S, p2:T)(implicit evd:S=:=T):(S, T) = {
    p1 -> p2
  }
  // do not compile now
//  println(
//    checkPair3(1, "test1")
//  )
//  println(
//    checkPair3(23, 23L)
//  )
  def checkPair4[S, T](p1:S, p2:T)(implicit evd:S<:<T):(S, T) = {
    p1 -> p2
  }
  // do not compile now
//  println(
//    checkPair4(1, "test1")
//  )
  println(
    checkPair4(IndexedSeq(1), Seq(1))
  )

}
