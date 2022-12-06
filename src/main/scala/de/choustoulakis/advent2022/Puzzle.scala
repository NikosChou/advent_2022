package de.choustoulakis.advent2022

trait Puzzle[I, O]:
  private val interfaceName: String = getClass.getInterfaces
    .map(_.getSimpleName)
    .filter(_.contains("Day"))
    .head
  val day: Int = """\d+""".r.findFirstIn(interfaceName).map(_.toInt).get

  def solve(input: I): O

object Puzzle:
  val NEW_LINE = "\\n"