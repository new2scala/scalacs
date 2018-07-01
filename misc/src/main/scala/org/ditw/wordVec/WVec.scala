package org.ditw.wordVec

case class WVec(w:String, v:Array[Double])

object WVec {
  private val spaceSplitter = """\s+""".r
  def fromLine(line:String):WVec = {
    val parts = spaceSplitter.split(line)
    val w = parts(0)
    val v = (1 until parts.length).map(i => parts(i).toDouble).toArray
    WVec(w, v)
  }


}


