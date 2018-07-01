package org.ditw.wordVec

import java.util

trait VecOps[T] {
  def dotProd(v1:T, v2:T):Double
  def cosineSimilarity(v1:T, v2:T):Double
  def dist(v1:T, v2:T):Double
  def minus(v1:T, v2:T):T
  def len(v1:T):Double
  def zero(len:Int):T
}

object VecOps {

  private val NA = ""

  object Implicits {
    implicit val wvecOps:VecOps[WVec] = new VecOps[WVec] {
      override def minus(v1: WVec, v2: WVec): WVec = {
        assert(v1.v.length == v2.v.length)
        WVec(s"${v1.w}-${v2.w}", v1.v.indices.map(idx => v1.v(idx)-v2.v(idx)).toArray)
      }
      override def dotProd(v1: WVec, v2: WVec): Double = {
        assert(v1.v.length == v2.v.length)
        v1.v.indices.map(idx => v1.v(idx)*v2.v(idx)).sum
      }
      override def zero(len: Int): WVec = {
        val r = new Array[Double](len)
        util.Arrays.fill(r, 0.0)
        WVec("", r)
      }
      override def len(v1: WVec): Double = {
        dist(v1, zero(v1.v.length))
      }
      override def cosineSimilarity(v1: WVec, v2: WVec): Double = {
        assert(v1.v.length == v2.v.length)
        dotProd(v1, v2) / (len(v1)*len(v2))
      }

      override def dist(v1: WVec, v2: WVec): Double = {
        assert(v1.v.length == v2.v.length)
        math.sqrt(
          v1.v.indices.map { idx =>
            val d = v1.v(idx)-v2.v(idx)
            d*d
          }.sum
        )
      }
    }
  }

  def dotProd[T:VecOps](v1:T, v2:T):Double = {
    implicitly[VecOps[T]].dotProd(v1, v2)
  }
  def cosineSimilarity[T:VecOps](v1:T, v2:T):Double = {
    implicitly[VecOps[T]].cosineSimilarity(v1, v2)
  }

  def minus[T:VecOps](v1:T, v2:T):T = {
    implicitly[VecOps[T]].minus(v1, v2)
  }

  def sortBySimilarity[T:VecOps](v0:T, others:IndexedSeq[T]):IndexedSeq[(T, Double)] = {
    val sim2Vec = others.map(o => o -> cosineSimilarity(v0, o))
    sim2Vec.sortBy(_._2)(Ordering[Double].reverse)
  }
}