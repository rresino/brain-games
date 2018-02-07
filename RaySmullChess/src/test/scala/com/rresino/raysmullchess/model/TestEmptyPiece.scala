package com.rresino.raysmullchess.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestEmptyPiece extends TestBasePiece {

  test("Empty moves") {
    (0 to 100).foreach(p =>
      assert(EmptyPiece.moves(b, p) === Array.empty))
  }
}
