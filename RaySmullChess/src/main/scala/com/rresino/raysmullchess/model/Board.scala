package com.rresino.raysmullchess.model

class Board(totalRows: Int, totalCols: Int) {

  val positions: Array[Piece] = new Array[Piece](totalRows * totalCols)
    .map(_ => EmptyPiece)

  def clean: Unit = {
    (totalRows until totalCols).map(positions(_) = EmptyPiece)
  }

  def getRow(position: Int): Int = position / totalCols

  def getCol(position: Int): Int = position % totalCols

  def getPosition(row: Int, col: Int): Int = totalCols * row + col

  def getPiece(position: Int): Piece = positions(position)

  def getPiece(row: Int, col: Int): Piece = getPiece(getPosition(row, col))

  def movePiece(fromPosition: Int, toPosition: Int): Board = {
    positions(toPosition) = getPiece(fromPosition)
    positions(fromPosition) = EmptyPiece
    this
  }

  def addPiece(fromPosition: (Int, Int), piece: Piece): Unit = {
    addPiece(getPosition(fromPosition._1, fromPosition._2), piece)
  }

  def addPiece(fromPosition: Int, piece: Piece): Unit = {
    positions(fromPosition) = piece
  }

  def getRelativePosition(position: Int, rowOffset: Int, colOffset: Int) = {

    val fixedCol = getCol(position) + colOffset
    val fixedRow = getRow(position) + rowOffset
    if (fixedCol >= 0 && fixedCol < totalCols && fixedRow >= 0 && fixedRow < totalRows) {
      val relativePosition = getPosition(fixedRow, fixedCol)

      if (relativePosition >= 0 && relativePosition < positions.length) {
        relativePosition
      } else {
        -1
      }
    } else {
      -1
    }
  }

  def show(): Unit = {
    print("-" * (3 * totalCols + 1))
    (0 until (totalRows * totalCols)).foreach(i => {
      if (getCol(i) == 0) {
        println
        print("|")
      }
      print(positions(i).getName() + "|")
    })
    println
    println("-" * (3 * totalCols + 1))
  }

  def getMarksResults(marksPositions: Array[Int]): Array[(Int, Int)] =
    marksPositions.map(m => (m, getPiece(m).moves(this, m).length))

  def showMarksResults(result: Array[(Int, Int)]): Unit = {
    result.foreach((r) =>
        println(s"(${getRow(r._1)},${getCol(r._1)}) => ${r._2}")
    )
  }
}
