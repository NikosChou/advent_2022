package de.choustoulakis.advent2022

import de.choustoulakis.advent2022.Puzzle.Output

trait Puzzle[+A <: Output[_]]:
  private val interfaceName: String = getClass.getInterfaces
    .map(_.getSimpleName)
    .filter(_.contains("Day"))
    .head

  val day: Int = """\d+""".r.findFirstIn(interfaceName).map(_.toInt).get

  def solve(input: String): A

object Puzzle:
  sealed abstract class Output[+A](part1: A, part2: A):
    def _1: A = part1

    def _2: A = part2

  class StringOutput(out1: String = "", out2: String = "") extends Output[String](out1, out2)

  class IntOutput(out1: Int = 0, out2: Int = 0) extends Output[Int](out1, out2)

  val NEW_LINE = "\\n"