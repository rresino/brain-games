package com.rresino.raysmullchess.model

import org.scalatest.FunSuite

abstract class TestBasePiece extends FunSuite {

  val b = new Board(8,8)

  def compare(exp: Array[Int], rs: Array[Int]) = {
    assert(rs.length == exp.length)
    exp.foreach(p => assert(rs.contains(p)))
    assert(exp.sorted === rs.sorted)
  }

  def show(rs: Array[Int]) =
    rs.foreach(p => println(s"(${b.getRow(p)},${b.getCol(p)})"))
}
