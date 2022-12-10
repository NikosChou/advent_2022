package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.MagicalEnergySpec
import de.choustoulakis.advent2022.days.Day9.Point
import org.scalatest.*

import scala.io.Source

class Day9Test extends MagicalEnergySpec with Day9 {
  "--- Day 9: Rope Bridge ---" when {

    "the point should touch" should {
      "given" in {
        val point = Point(5, 5)
        point.isTouching(Point(6, 4)) shouldBe true
        point.isTouching(Point(6, 5)) shouldBe true
        point.isTouching(Point(6, 6)) shouldBe true

        point.isTouching(Point(5, 4)) shouldBe true
        point.isTouching(Point(5, 5)) shouldBe true
        point.isTouching(Point(5, 6)) shouldBe true

        point.isTouching(Point(4, 4)) shouldBe true
        point.isTouching(Point(4, 5)) shouldBe true
        point.isTouching(Point(4, 6)) shouldBe true

        point.isTouching(Point(5, 7)) shouldBe false
        point.isTouching(Point(5, 3)) shouldBe false

        point.isTouching(Point(7, 5)) shouldBe false
        point.isTouching(Point(3, 5)) shouldBe false

        point.isTouching(Point(7, 7)) shouldBe false
        point.isTouching(Point(4, 3)) shouldBe false
        point.isTouching(Point(4, 7)) shouldBe false
      }
    }


    "given test input" should {
      "should solve part 1" in {
        val input = "R 4\nU 4\nL 3\nD 1\nR 4\nD 1\nL 5\nR 2"
        val actual = solve(input)
        actual._1 shouldBe 13
      }

      "should solve part 2" in {
        val input = "R 5\nU 8\nL 8\nD 3\nR 17\nD 10\nL 25\nU 20"
        val actual = solve(input)
        actual._2 shouldBe 36
      }
    }

    "given my input" should {
      val input = resource
      val actual = solve(input)
      "should solve part 1" in {
        actual._1 shouldBe 6406
      }

      "should solve part 2" in {
        // 11293
        actual._2 shouldBe 2643
      }
    }

  }
}
