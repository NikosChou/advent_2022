package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.MagicalEnergySpec
import org.scalatest.*

import scala.io.Source

class Day3Test extends MagicalEnergySpec with Day3 {
  "--- Day 3: Rucksack Reorganization ---" when {

    "test input given" should {
      val input = "vJrwpWtwJgWrhcsFMMfFFhFp\njqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\nPmmdzqPrVvPwwTWBwg\nwMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\nttgJtRGJQctTZtZT\nCrZsJsPPZsGzwwsLwLmpwMDw"
      val actual = solve(input)
      "should solve part 1" in {
        actual._1 shouldBe 157
      }

      "should solve part 2" in {
        actual._2 shouldBe 70
      }
    }

    "my input given" should {
      val actual = solve(resource)
      "should solve part 1" in {
        actual._1 shouldBe 8153
      }

      "should solve part 2" in {
        actual._2 shouldBe 2342
      }
    }
  }
}
