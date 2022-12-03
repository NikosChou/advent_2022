package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.MagicalEnergySpec
import de.choustoulakis.advent2022.days.Day1.Elves
import org.scalatest.*

import scala.io.Source

class Day2Test extends MagicalEnergySpec with Day2 {
  "--- Day 2: Rock Paper Scissors ---" when {

    "test input given" should {
      val input = "A Y\nB X\nC Z"
      val actual = solve(input)
      "count the score strategy 1" in {
        actual._1 shouldBe 15
      }

      "count the score strategy 2" in {
        actual._2 shouldBe 12
      }
    }

    "my input given" should {
      val actual = solve(resource)
      "count the score strategy 1" in {
        actual._1 shouldBe 13675
      }

      "count the score strategy 2" in {
        actual._2 shouldBe 14184
      }
    }
  }
}
