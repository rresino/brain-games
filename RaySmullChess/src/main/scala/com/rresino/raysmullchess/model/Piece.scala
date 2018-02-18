package com.rresino.raysmullchess.model

import scala.annotation.tailrec

trait Piece {

  def moves(board: Board, position: Int): Array[Int]

  def getName(): String
}

object EmptyPiece extends Piece {
  override def moves(board: Board, position: Int): Array[Int] = Array.empty

  override def getName(): String = "  "
}

object Rook extends Piece {
  override def moves(board: Board, position: Int): Array[Int] = {

    val offset = (1 to 7)

    val pos =
      offset.map(os => board.getRelativePosition(position, os, 0)) ++
      offset.map(os => board.getRelativePosition(position, -os, 0)) ++
      offset.map(os => board.getRelativePosition(position, 0, os)) ++
      offset.map(os => board.getRelativePosition(position, 0, -os))

    pos.filter(p => p != -1).toArray
  }

  override def getName(): String = "R "
}

object Knight extends Piece {
  override def moves(board: Board, position: Int): Array[Int] = Array(
    board.getRelativePosition(position, -2, -1),
    board.getRelativePosition(position, -2, 1),
    board.getRelativePosition(position, -1, -2),
    board.getRelativePosition(position, 1, -2),
    board.getRelativePosition(position, -1, 2),
    board.getRelativePosition(position, 1, 2),
    board.getRelativePosition(position, 2, -1),
    board.getRelativePosition(position, 2, 1),
  ).filter(p => p != -1)

  override def getName(): String = "Kn"
}

object Bishop extends Piece {
  override def moves(board: Board, position: Int): Array[Int] = {
    val offset = (1 to 7)

    val pos =
      offset.map(os => board.getRelativePosition(position, -os, -os)) ++
        offset.map(os => board.getRelativePosition(position, -os, os)) ++
        offset.map(os => board.getRelativePosition(position, os, -os)) ++
        offset.map(os => board.getRelativePosition(position, os, os))

    pos.filter(p => p != -1).toArray
  }

  override def getName(): String = "B "
}

object Queen extends Piece {
  override def moves(board: Board, position: Int): Array[Int] =
    (Rook.moves(board, position) ++
    Bishop.moves(board, position)).filter(p => p != -1)

  override def getName(): String = "Q "
}

object King extends Piece {
  override def moves(board: Board, position: Int): Array[Int] = Array(
      board.getRelativePosition(position, -1, -1),
      board.getRelativePosition(position, -1, 0),
      board.getRelativePosition(position, -1, 1),
      board.getRelativePosition(position, 0, -1),
      board.getRelativePosition(position, 0, 1),
      board.getRelativePosition(position, 1, -1),
      board.getRelativePosition(position, 1, 0),
      board.getRelativePosition(position, 1, 1)
  ).filter(p => p != -1)

  override def getName(): String = "Ki"
}

object Mark extends Piece {

  override def moves(board: Board, markPosition: Int): Array[Int] = {
    movesIter(board, markPosition, board.positions.indices.toList, Array())
  }

  @tailrec
  protected def movesIter(board: Board,
                          markPosition: Int, indexes: List[Int],
                          acc: Array[Int]): Array[Int] = indexes match {
    case Nil => acc
    case index :: tail => {
      if (markPosition != index && attackMark(board, index, markPosition)) {
        movesIter(board, markPosition, tail, acc :+ index)
      } else {
        movesIter(board, markPosition, tail, acc)
      }
    }
  }

  protected def attackMark(board: Board, piecePosition: Int, markPosition: Int): Boolean = {

    val piece = board.getPiece(piecePosition)

    piece match {
      case p: Mark.type => false
      case p => board.getPiece(piecePosition)
        .moves(board, piecePosition)
        .filter(p => p == markPosition)
        .isEmpty == false
    }
  }

  override def getName(): String = "* "
}
