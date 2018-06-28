package org.ditw.scalazTests

object CharToyTests extends App {
  import CharToy._

  import Fix._
  println(trace(output('B', done)))
  println(trace(fix(output('B', fix(done)))))
  println(trace(bell(output('C', done))))
}
