package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.Puzzle
import de.choustoulakis.advent2022.Puzzle.IntOutput
import de.choustoulakis.advent2022.days.Day9.{Board, Command, Point}

import scala.annotation.tailrec


trait Day9 extends Puzzle[IntOutput] :

  override def solve(in: String): IntOutput =
    def moveDiagonal(h: Point, t: Point): Point =
      if (t.moveLeft.moveUp.isTouching(h)) t.moveLeft.moveUp
      else if (t.moveLeft.moveDown.isTouching(h)) t.moveLeft.moveDown
      else if (t.moveRight.moveDown.isTouching(h)) t.moveRight.moveDown
      else if (t.moveRight.moveUp.isTouching(h)) t.moveRight.moveUp
      else t

    def moveCross(h: Point, t: Point): Point =
      if (t.moveLeft.isTouching(h)) t.moveLeft
      else if (t.moveDown.isTouching(h)) t.moveDown
      else if (t.moveRight.isTouching(h)) t.moveRight
      else if (t.moveUp.isTouching(h)) t.moveUp
      else t

    @tailrec
    def calculatePositionAfter(board: Board, cmd: Command): Board =
      if (cmd.num == 0)
        board
      else {
        val fn: Point => Point = cmd match
          case Command('R', _) => _.moveRight
          case Command('U', _) => _.moveUp
          case Command('L', _) => _.moveLeft
          case Command('D', _) => _.moveDown

        val follow: (Point, Point) => Point = (h, t) =>
          if (t.isTouching(h)) t else {
            if (h.i == t.i || h.j == t.j) moveCross(h, t)
            else moveDiagonal(h, t)
          }

        val newRope = board.rope.tail.foldLeft(List(fn(board.rope.head))) { (acc, e) =>
          acc.:+(follow(acc.last, e))
        }
        val visited = board.visitedFromTail.+(newRope.last)

        calculatePositionAfter(Board(newRope, visited), cmd.copy(num = cmd.num - 1))
      }

    val startPosition = Point(0, 0)
    val commands = in.split(Puzzle.NEW_LINE)
      .map {
        case s"$dir $num" => Command(dir.charAt(0), num.toInt)
      }
    val board = commands.foldLeft(Board(List(startPosition, startPosition), Set(startPosition))) { (acc, next) =>
      calculatePositionAfter(acc, next)
    }

    val rope = (1 to 10).map(_ => startPosition).toList
    val board2 = commands.foldLeft(Board(rope, Set(startPosition))) { (acc, next) =>
      calculatePositionAfter(acc, next)
    }


    new IntOutput(board.visitedFromTail.size, board2.visitedFromTail.size)

object Day9:
  case class Board(rope: List[Point], visitedFromTail: Set[Point])

  case class Command(dir: Char, num: Int)

  case class Point(i: Int, j: Int):
    def moveRight: Point = Point(i, j + 1)

    def moveUp: Point = Point(i + 1, j)

    def moveLeft: Point = Point(i, j - 1)

    def moveDown: Point = Point(i - 1, j)

    def isTouching(p: Point): Boolean = (i - p.i).abs < 2 && (j - p.j).abs < 2