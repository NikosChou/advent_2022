package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.Puzzle
import de.choustoulakis.advent2022.Puzzle.IntOutput
import de.choustoulakis.advent2022.days.Day8.Node


trait Day8 extends Puzzle[IntOutput] :
  override def solve(in: String): IntOutput =
    val trees = in.split(Puzzle.NEW_LINE).map(_.split("").map(_.toInt))

    def isVisible(height: Int, i: Int, j: Int): Boolean =
      def visibilityCheck(ls: Array[Int]): Boolean = ls.forall(height > _)

      visibilityCheck(trees(i).take(j))
        || visibilityCheck(trees(i).drop(j + 1))
        || visibilityCheck(trees.map(a => a(j)).take(i))
        || visibilityCheck(trees.map(a => a(j)).drop(i + 1))

    def score(height: Int, i: Int, j: Int): Int =
      def findDistance(ls: Array[Int]): Int =
        val nextIndexOf = ls.indexWhere(_ >= height)
        if (nextIndexOf >= 0) nextIndexOf + 1
        else ls.length


      findDistance(trees(i).take(j).reverse)
        * findDistance(trees(i).drop(j + 1))
        * findDistance(trees.map(a => a(j)).take(i).reverse)
        * findDistance(trees.map(a => a(j)).drop(i + 1))

    val visibleTrees = for {
      i <- trees.indices
      j <- trees.head.indices
      height = trees(i)(j)
      if isVisible(height, i, j)
      scenicScore = score(height, i, j)
    } yield Node(height, i, j, scenicScore)

    val part1 = visibleTrees.size
    val part2 = visibleTrees.map(_.score).max
    new IntOutput(part1, part2)

object Day8:
  case class Node(height: Int, i: Int, j: Int, score: Int)
