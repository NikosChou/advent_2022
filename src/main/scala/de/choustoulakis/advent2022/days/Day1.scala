package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.Puzzle
import de.choustoulakis.advent2022.days.Day1.{Elf, Elves, Output}


trait Day1 extends Puzzle[Elves, Output] :
  val day = 1

  override def solve(elves: Elves): Output =
    val elfPerCalories: Ordering[Elf] = (l, r) => l.maxCalories - r.maxCalories
    val part1 = elves.max(elfPerCalories).maxCalories
    val part2 = elves.sorted(elfPerCalories.reverse).take(3).map(_.maxCalories).sum

    (part1, part2)


object Day1:
  type Elves = List[Elf]
  type Output = (Int, Int)

  case class Elf(calories: List[Int]):
    lazy val maxCalories: Int = calories.sum


  given Conversion[String, Elves] with
    def apply(in: String): Elves = in.split("\\n\\n")
      .map(_.split(Puzzle.NEW_LINE).map(_.toInt).toList)
      .map(Elf.apply)
      .toList

