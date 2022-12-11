package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.MagicalEnergySpec
import de.choustoulakis.advent2022.days.Day9.Point
import org.scalatest.*

import scala.io.Source

class Day11Test extends MagicalEnergySpec with Day11 {
  "--- Day 11: Monkey in the Middle ---" when {

    "given test input" should {
      val input = "Monkey 0:\n  Starting items: 79, 98\n  Operation: new = old * 19\n  Test: divisible by 23\n    If true: throw to monkey 2\n    If false: throw to monkey 3\n\nMonkey 1:\n  Starting items: 54, 65, 75, 74\n  Operation: new = old + 6\n  Test: divisible by 19\n    If true: throw to monkey 2\n    If false: throw to monkey 0\n\nMonkey 2:\n  Starting items: 79, 60, 97\n  Operation: new = old * old\n  Test: divisible by 13\n    If true: throw to monkey 1\n    If false: throw to monkey 3\n\nMonkey 3:\n  Starting items: 74\n  Operation: new = old + 3\n  Test: divisible by 17\n    If true: throw to monkey 0\n    If false: throw to monkey 1"
      val actual = solve(input)
      "should solve part 1" in {
        actual._1 shouldBe 10605
      }

      "should solve part 2" in {
        actual._2 shouldBe 2713310158L
      }
    }

    "given my input" should {
      val input = resource
      val actual = solve(input)
      "should solve part 1" in {
        actual._1 shouldBe 107822
      }

      "should solve part 2" in {
        actual._2 shouldBe 27267163742L
      }
    }

  }
}
