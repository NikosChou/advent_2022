package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.MagicalEnergySpec
import de.choustoulakis.advent2022.days.Day9.Point
import org.scalatest.*

import scala.io.Source

class Day12Test extends MagicalEnergySpec with Day12 {
  "--- Day 12: Hill Climbing Algorithm ---" when {

    "given test input" should {
      val input = "Sabqponm\nabcryxxl\naccszExk\nacctuvwj\nabdefghi"
      val actual = solve(input)
      "should solve part 1" in {
        actual._1 shouldBe 31
      }

      "should solve part 2" in {
        actual._2 shouldBe 29
      }
    }

    "given my input" should {
      val input = resource
      val actual = solve(input)
      "should solve part 1" in {
        actual._1 shouldBe 456
      }

      "should solve part 2" in {
        actual._2 shouldBe 454
      }
    }

  }
}
