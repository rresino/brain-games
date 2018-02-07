package com.rresino.raysmullchess.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestRookPiece  extends TestBasePiece {

  test("Rook moves center") {

    val exp = Array(
      b.getPosition(0,3),
      b.getPosition(1,3),
      b.getPosition(2,3),
      b.getPosition(4,3),
      b.getPosition(5,3),
      b.getPosition(6,3),
      b.getPosition(7,3),
      b.getPosition(3,0),
      b.getPosition(3,1),
      b.getPosition(3,2),
      b.getPosition(3,4),
      b.getPosition(3,5),
      b.getPosition(3,6),
      b.getPosition(3,7)
    )

    val rs = Rook.moves(b, b.getPosition(3,3))
    compare(exp, rs)
  }

  test("Rook moves top upper") {
    val exp = Array(
      b.getPosition(0,1),
      b.getPosition(0,2),
      b.getPosition(0,3),
      b.getPosition(0,4),
      b.getPosition(0,5),
      b.getPosition(0,6),
      b.getPosition(0,7),
      b.getPosition(1,0),
      b.getPosition(2,0),
      b.getPosition(3,0),
      b.getPosition(4,0),
      b.getPosition(5,0),
      b.getPosition(6,0),
      b.getPosition(7,0)
    )

    val rs = Rook.moves(b, b.getPosition(0,0))
    compare(exp, rs)
  }
}
