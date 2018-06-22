package org.ditw.scalazTests

object CharToyTests extends App {
  import CharToy._

  println(trace(output('B', done)))

  println(trace(bell(output('C', done))))
}
