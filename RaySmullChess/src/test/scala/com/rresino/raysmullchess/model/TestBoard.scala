package com.rresino.raysmullchess.model

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestBoard  extends FunSuite {

  val expBoard: Seq[(Int,Int,Int)] =
    for {
        r <- 0 until 5
        c <- 0 until 5
    } yield (((r*5+c), r,c))

  test("init a new board") {

    val b = new Board(5,5)

    assert(b.positions.length == (5 * 5), "Bad number position")

    b.positions.foreach(p => {
      assert(p === EmptyPiece)
    })
  }

  test("get row and col") {

    val b = new Board(5,5)

    expBoard.foreach(p => {
      assert(b.getCol(p._1) === p._3)
      assert(b.getRow(p._1) === p._2)
      assert(p._1 === b.getPosition(p._2,p._3))
    })
  }
}
