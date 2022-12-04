package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.Puzzle

trait Day3 extends Puzzle[String, (Int, Int)] :
  val day = 3

  override def solve(in: String): (Int, Int) =

    val charNumberToInt: Char => Int = _.toInt match {
      case i if i >= 97 => i - 96
      case i => i - 38
    }

    val part1 = in.split(Puzzle.NEW_LINE)
      .map(line => line.splitAt(line.length / 2))
      .map(lines => lines(0).filter(char => lines(1).contains(char)).head)
      .map(charNumberToInt)
      .sum

    val part2 = in.split(Puzzle.NEW_LINE)
      .grouped(3)
      .map(lines => lines.head.filter(c => lines(1).contains(c.toString) && lines(2).contains(c.toString)).head)
      .map(charNumberToInt).sum

    (part1, part2)

