package org.ditw.scalazTests

import scala.reflect.runtime.universe._

sealed trait CharToy[+Next]

object CharToy {
  case class CharOutput[Next](a:Char, next:Next) extends CharToy[Next] {
    override def toString: String = s"Output: $a"
  }
  case class CharBell[Next](next:Next) extends CharToy[Next] {
    override def toString: String = s"Bell: $next"
  }
  case object CharDone extends CharToy[Nothing] {
    override def toString: String = s"Done"
  }

  def output[Next](a:Char, next:Next):CharToy[Next] = {
    CharOutput(a, next)
  }
  def bell[Next](next:Next):CharToy[Next] = {
    CharBell(next)
  }
  def done:CharToy[Nothing] = CharDone

  def trace[Next:TypeTag](v:Next):String = {
    val ty = implicitly[TypeTag[Next]].toString()
    s"$ty: $v"
  }


}
