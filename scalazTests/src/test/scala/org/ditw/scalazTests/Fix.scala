package org.ditw.scalazTests

case class Fix[F[_]](f:F[Fix[F]])

object Fix {
  def fix(toy:CharToy[Fix[CharToy]]):Fix[CharToy] =
    Fix[CharToy](toy)
}