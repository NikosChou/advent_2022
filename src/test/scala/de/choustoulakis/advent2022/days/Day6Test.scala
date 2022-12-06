package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.MagicalEnergySpec
import org.scalatest.*

import scala.io.Source

class Day6Test extends MagicalEnergySpec with Day6 {
  "--- Day 6: Tuning Trouble ---" when {
    "given test input" should {
      val input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
      val actual = solve(input)
      "should solve part 1" in {
        actual._1 shouldBe 7
      }

      "should solve part 2" in {
        actual._2 shouldBe 19
      }
    }

    "given my input" should {
      val input = resource
      val actual = solve(input)
      "should solve part 1" in {
        actual._1 shouldBe 1833
      }

      "should solve part 2" in {
        actual._2 shouldBe 3425
      }
    }

  }
}
