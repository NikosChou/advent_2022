package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.Puzzle
import de.choustoulakis.advent2022.Puzzle.IntOutput
import de.choustoulakis.advent2022.days.Day1.{Elf, Elves}


trait Day1 extends Puzzle[IntOutput] :

  override def solve(input: String): IntOutput =
    val elves = Elves(input).elves
    val elfPerCalories: Ordering[Elf] = (l, r) => l.maxCalories - r.maxCalories
    val part1 = elves.max(elfPerCalories).maxCalories
    val part2 = elves.sorted(elfPerCalories.reverse).take(3).map(_.maxCalories).sum

    new IntOutput(part1, part2)


object Day1:
  case class Elf(calories: List[Int]):
    lazy val maxCalories: Int = calories.sum

  case class Elves(elves: List[Elf])

  object Elves:
    def apply(in: String): Elves = Elves(in.split("\\n\\n")
      .map(_.split(Puzzle.NEW_LINE).map(_.toInt).toList)
      .map(Elf.apply)
      .toList)

