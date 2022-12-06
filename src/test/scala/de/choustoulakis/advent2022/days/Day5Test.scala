package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.MagicalEnergySpec
import org.scalatest.*

import scala.io.Source

class Day5Test extends MagicalEnergySpec with Day5 {
  "--- Day 5: Supply Stacks ---" when {

    "test input given" should {
      val input = "    [D]    \n[N] [C]    \n[Z] [M] [P]\n 1   2   3 \n\nmove 1 from 2 to 1\nmove 3 from 1 to 3\nmove 2 from 2 to 1\nmove 1 from 1 to 2"
      val actual = solve(input)
      "should solve part 1" in {
        actual._1 shouldBe "CMZ"
      }

      "should solve part 2" in {
        actual._2 shouldBe "MCD"
      }
    }

    "given my input" should {
      val input = resource
      val actual = solve(input)
      "should solve part 1" in {
        actual._1 shouldBe "QGTHFZBHV"
      }

      "should solve part 2" in {
        actual._2 shouldBe "MGDMPSZTM"
      }
    }

  }
}
