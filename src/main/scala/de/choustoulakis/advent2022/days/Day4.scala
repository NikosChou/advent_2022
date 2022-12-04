package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.Puzzle
import de.choustoulakis.advent2022.days.Day4.{PairSection, Section}

trait Day4 extends Puzzle[String, (Int, Int)] :
  val day = 4

  override def solve(in: String): (Int, Int) =
    val part1 = in.split(Puzzle.NEW_LINE)
      .map(PairSection.apply)
      .count(_.contains)

    val part2 = in.split(Puzzle.NEW_LINE)
      .map(PairSection.apply)
      .count(_.overlaps)

    (part1, part2)

object Day4:
  case class Section(l: Int, r: Int):
    lazy val size: Int = (l to r).size;

  object Section:
    def apply(in: String): Section = {
      val ins = in.split("-")
      Section(ins(0).toInt, ins(1).toInt)
    }

  case class PairSection(l: Section, r: Section):
    lazy val contains: Boolean = {
      val long = if (l.size >= r.size) l else r
      val short = if (long == r) l else r
      if (long.l <= short.l && long.r >= short.r) true else false
    }

    lazy val overlaps: Boolean = {
      (for {
        le <- l.l to l.r
        re <- r.l to r.r
        if le == re
      } yield true).exists(identity)
    }


  object PairSection:
    def apply(line: String): PairSection =
      PairSection(Section(line.split(",")(0)), Section(line.split(",")(1)))