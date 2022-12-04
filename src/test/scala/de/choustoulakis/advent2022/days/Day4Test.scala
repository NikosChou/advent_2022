package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.MagicalEnergySpec
import org.scalatest.*

import scala.io.Source

class Day4Test extends MagicalEnergySpec with Day4 {
  "--- Day 4: Camp Cleanup ---" when {

    "test input given" should {
      val input = "2-3,4-5\n5-7,7-9\n2-8,3-7\n6-6,4-6\n2-6,4-8"
      val actual = solve(input)
      "should solve part 1" in {
        actual._1 shouldBe 2
      }

      "should solve part 2" in {
        actual._2 shouldBe 4
      }
    }

    "my input given" should {
      val actual = solve(resource)
      "should solve part 1" in {
        actual._1 shouldBe 573
      }

      "should solve part 2" in {
        actual._2 shouldBe 867
      }
    }
  }
}
