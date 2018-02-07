package com.rresino.raysmullchess.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestKnightPiece extends TestBasePiece {

  test("Knight moves center") {

    val exp = Array(
      b.getPosition(1,2),
      b.getPosition(1,4),
      b.getPosition(2,1),
      b.getPosition(4,1),
      b.getPosition(5,2),
      b.getPosition(5,4),
      b.getPosition(2,5),
      b.getPosition(4,5)
    )

    val rs = Knight.moves(b, b.getPosition(3,3))
    compare(exp, rs)
  }

  test("Knight moves top upper") {
    val exp = Array(
      b.getPosition(1,2),
      b.getPosition(2,1)
    )

    val rs = Knight.moves(b, b.getPosition(0,0))
    compare(exp, rs)
  }
}
