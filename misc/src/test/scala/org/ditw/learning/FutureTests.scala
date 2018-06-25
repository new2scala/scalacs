package org.ditw.learning

import java.time.Instant
import java.time.format.DateTimeFormatter

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Random, Success}

object FutureTests extends App {

  def dbgMsg(msg:String):Unit = {
    val now = DateTimeFormatter.ISO_INSTANT
      .format(Instant.now())
    val timeInfo = now.substring(11, 23)

    val thread = Thread.currentThread().getName

    println(s"$timeInfo [$thread] $msg")
  }


  dbgMsg("test dbgMsg")
  import concurrent.duration._
  import scala.concurrent.ExecutionContext.Implicits.global

  def test1_1taskBlock:Unit = {
    val f = Future {
      Thread.sleep(500)
      1+1
    }

    val res = Await.result(f, 1 second)
    dbgMsg(s"result: $res")
  }
  //test1_1taskBlock

  def test2_1taskCallback:Unit = {
    dbgMsg("b4 calculation")
    val f = Future {
      Thread.sleep(500)
      Random.nextInt
    }
    dbgMsg("after calculation")
    f.onComplete {
      case Success(v) => dbgMsg(s"Got result: $v")
      case Failure(e) => dbgMsg(s"failed with exception: ${e.getMessage}")
    }

    dbgMsg("A ..."); Thread.sleep(200)
    dbgMsg("B ..."); Thread.sleep(200)
    dbgMsg("C ..."); Thread.sleep(200)
    dbgMsg("D ..."); Thread.sleep(200)
    dbgMsg("E ..."); Thread.sleep(200)
    dbgMsg("F ..."); Thread.sleep(200)

    val f2 = Future {
      Thread.sleep(250)
      val v = Random.nextInt(500)
      if (v < 250) throw new RuntimeException(s"ex $v")
      v
    }

    f2.onSuccess {
      case result => dbgMsg(s"f2 result: $result")
    }
    f2.onFailure {
      case ex => dbgMsg(s"f2 exception: ${ex.getMessage}")
    }
    Thread.sleep(2000) // ensure task runs to completion
  }
  //test2_1taskCallback

  private def ff1 = Future {
    Thread.sleep(250)
    val v = Random.nextInt(500)
    if (v < 250) throw new RuntimeException(s"ex $v")
    v
  }

  def test3_ForComprehension:Unit = {
    dbgMsg("b4 for")
    val res = for {
      r1 <- ff1
      r2 <- ff1
    } yield r1+r2
    dbgMsg("after for")
    res.onComplete {
      case Success(v) => dbgMsg(s"Success: $v")
      case Failure(ex) => dbgMsg(s"Failed: ${ex.getMessage}")
    }
    Thread.sleep(1000)
  }

  test3_ForComprehension
}
