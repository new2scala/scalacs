package org.ditw.learning.bigdl

object WordVecTest1 extends App {

  val testSents = Array(
    "He is the king",
    "The king is royal",
    "She is royal queen"
  )

  val lowerTokens = testSents.map(_.split("\\s+").map(_.toLowerCase()))
  val allVoc = lowerTokens.flatten.toSet.toIndexedSeq

  val dict:Map[String, Int] = allVoc.indices.map(idx => allVoc(idx) -> (idx+1)).toMap

  println(dict)

  val windowSize = 2

  val pairs = lowerTokens.flatMap { sent =>
    for (
      i <- sent.indices;
      j <- i-windowSize to i+windowSize if (i!=j && j >= 0 && j < sent.length)
    ) yield sent(i) -> sent(j)
  }

  println(pairs)

  val encoded = pairs.map(p => dict(p._1) -> dict(p._2))
  println(encoded)
}
