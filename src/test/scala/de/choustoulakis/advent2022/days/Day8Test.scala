package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.MagicalEnergySpec
import org.scalatest.*

import scala.io.Source

class Day8Test extends MagicalEnergySpec with Day8 {
  "--- Day 8: Treetop Tree House ---" when {
    "given test input" should {
      val input = "30373\n25512\n65332\n33549\n35390"
      val actual = solve(input)
      "should solve part 1" in {
        actual._1 shouldBe 21
      }

      "should solve part 2" in {
        actual._2 shouldBe 8
      }
    }

    "given my input" should {
      val input = resource
      val actual = solve(input)
      "should solve part 1" in {
        actual._1 shouldBe 1703
      }

      "should solve part 2" in {
        actual._2 shouldBe 496650
      }
    }

  }
}
