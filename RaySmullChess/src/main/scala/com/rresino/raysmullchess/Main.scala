package com.rresino.raysmullchess

import com.rresino.raysmullchess.model._

object Main extends App {

  val piecesPositions: Array[(Int, Int)] = Array((0,3), (1,6), (3,1), (5,2),(5,4))
  val marksPositions: Array[(Int, Int)] = Array((1,0), (3,6),(7,3))

  def getPiecePosition(b: Board)(x: Int) = b.getPosition(piecesPositions(x)._1, piecesPositions(x)._2)

  val pieces = Array(Rook, Knight, Bishop, Queen, King)

  val boards: Array[Board]=
    for {
      z <- pieces;
      x <- pieces.filter(_ != z);
      c <- pieces.filter(a => a != z && a != x);
      v <- pieces.filter(a => a != z && a != x && a != c);
      b <- pieces.filter(a => a != z && a != x && a != c && a != v)
    } yield {
      val board = createBoard()

      board.addPiece(getPiecePosition(board)(0), z)
      board.addPiece(getPiecePosition(board)(1), x)
      board.addPiece(getPiecePosition(board)(2), c)
      board.addPiece(getPiecePosition(board)(3), v)
      board.addPiece(getPiecePosition(board)(4), b)
      addMarks(board)
      board
    }

  def createBoard(): Board = new Board(8,8)

  def addMarks(b: Board): Board = {
    marksPositions.foreach(m => b.addPiece(m, Mark))
    b
  }

  boards.foreach(b => {

    val markresults = b.getMarksResults(marksPositions.map(p => b.getPosition(p._1,p._2)))

    if (markresults.foldLeft(true)((acc, elem) => acc && elem._2 == 2)) {
      println()
      b.show()
      b.showMarksResults(markresults)
      println()
      println()
    }
  })
}
