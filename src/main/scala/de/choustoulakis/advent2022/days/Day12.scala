package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.Puzzle
import de.choustoulakis.advent2022.Puzzle.{IntOutput, LongOutput, StringOutput}
import de.choustoulakis.advent2022.days.Day12.*

import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.Try


trait Day12 extends Puzzle[IntOutput] :
  override def solve(in: String): IntOutput =
    val input = in.split(Puzzle.NEW_LINE).map(_.split("").map {
      case "E" => 'z'
      case "S" => 'a'
      case c => c.head
    }.map(c => c - 'a'))

    val points = for {
      i <- input.indices
      j <- input(i).indices
    } yield Point(i, j, input(i)(j))

    val graph: Graph[Point] = p => Set(
      (p.i - 1, p.j), (p.i, p.j - 1),
      (p.i + 1, p.j), (p.i, p.j + 1)
    ).collect { case (i, j) => Try(Point(i, j, input(i)(j)))
      .filter(pp => pp.height - p.height < 2)
      .toOption
    }.flatten

    val lineLength = in.split(Puzzle.NEW_LINE).head.length
    val foo = in.split(Puzzle.NEW_LINE).mkString
    val first = Point(foo.indexOf("S") / lineLength, foo.indexOf("S") % lineLength, 0)
    val goal = Point(foo.indexOf("E") / lineLength, foo.indexOf("E") % lineLength, 'z' - 'a')

    val part2 = points.filter(_.height == 0).map(a => Day12.shortestPathNodes(graph)(a, goal)).filter(_ > 0).min
    val part1 = Day12.shortestPathNodes(graph)(first, goal)

    IntOutput(part1, part2)


object Day12:
  case class Point(i: Int, j: Int, height: Int)

  type Graph[N] = N => Set[N]

  def shortestPathNodes(g: Graph[Point])(source: Point, target: Point): Int =
    @tailrec
    def helper(tuple: (Int, Point), active: mutable.PriorityQueue[(Int, Point)], acc: Set[Point]): Int =
      val (nodes, p) = tuple

      if (p == target) nodes
      else if (active.isEmpty && (acc.contains(p) || g(p).isEmpty)) -1
      else if (acc.contains(p))
        helper(active.dequeue(), active, acc)
      else {
        g(p).foreach(p => active.enqueue((nodes + 1, p)))
        helper(active.dequeue(), active, acc + p)
      }


    val active = mutable.PriorityQueue[(Int, Point)]()(Ordering.by(-_._1))
    helper((0, source), active, Set())
