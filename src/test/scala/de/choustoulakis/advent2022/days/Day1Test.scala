package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.MagicalEnergySpec
import de.choustoulakis.advent2022.days.Day1.Elves
import org.scalatest.*

import scala.io.Source

class Day1Test extends MagicalEnergySpec with Day1 {
  "--- Day 1: Calorie Counting ---" when {

    "test input given" should {
      val input = "1000\n2000\n3000\n\n4000\n\n5000\n6000\n\n7000\n8000\n9000\n\n10000"
      val actual = solve(input)
      "count the max calories" in {
        actual._1 shouldBe 24000
      }

      "count the max calories of first 3 Elves" in {
        actual._2 shouldBe 45000
      }
    }

    "my input given" should {
      val actual = solve(resource)
      "count the max calories" in {
        actual._1 shouldBe 66616
      }

      "count the max calories of first 3 Elves" in {
        actual._2 shouldBe 199172
      }
    }
  }
}
