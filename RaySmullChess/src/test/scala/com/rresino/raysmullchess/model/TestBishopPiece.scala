package com.rresino.raysmullchess.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestBishopPiece  extends TestBasePiece {

  test("Bishop moves center") {

    val exp = Array(
      b.getPosition(0,0),
      b.getPosition(1,1),
      b.getPosition(2,2),
      b.getPosition(4,4),
      b.getPosition(5,5),
      b.getPosition(6,6),
      b.getPosition(7,7),
      b.getPosition(4,2),
      b.getPosition(5,1),
      b.getPosition(6,0),
      b.getPosition(2,4),
      b.getPosition(1,5),
      b.getPosition(0,6)
    )

    val rs = Bishop.moves(b, b.getPosition(3,3))
    compare(exp, rs)
  }

  test("Bishop moves top upper") {
    val exp = (1 until 8)
      .map(p => b.getPosition(p,p))
      .toArray

    val rs = Bishop.moves(b, b.getPosition(0,0))
    compare(exp, rs)
  }
}
