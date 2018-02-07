package com.rresino.raysmullchess.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestKingPiece  extends TestBasePiece {

  test("King moves center") {

    val exp = Array(
      b.getPosition(2,2),
      b.getPosition(2,3),
      b.getPosition(2,4),
      b.getPosition(3,2),
      b.getPosition(3,4),
      b.getPosition(4,2),
      b.getPosition(4,3),
      b.getPosition(4,4)
    )

    val rs = King.moves(b, b.getPosition(3,3))
    compare(exp, rs)
  }

  test("King moves top upper") {
    val exp = Array(
      b.getPosition(0,1),
      b.getPosition(1,0),
      b.getPosition(1,1)
    )

    val rs = King.moves(b, b.getPosition(0,0))
    compare(exp, rs)
  }
}
