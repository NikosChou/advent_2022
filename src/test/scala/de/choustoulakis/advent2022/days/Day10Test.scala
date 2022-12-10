package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.MagicalEnergySpec
import de.choustoulakis.advent2022.days.Day9.Point
import org.scalatest.*

import scala.io.Source

class Day10Test extends MagicalEnergySpec with Day10 {
  "--- Day 10: Cathode-Ray Tube ---" when {

    "given test input" should {
      val input = "addx 15\naddx -11\naddx 6\naddx -3\naddx 5\naddx -1\naddx -8\naddx 13\naddx 4\nnoop\naddx -1\naddx 5\naddx -1\naddx 5\naddx -1\naddx 5\naddx -1\naddx 5\naddx -1\naddx -35\naddx 1\naddx 24\naddx -19\naddx 1\naddx 16\naddx -11\nnoop\nnoop\naddx 21\naddx -15\nnoop\nnoop\naddx -3\naddx 9\naddx 1\naddx -3\naddx 8\naddx 1\naddx 5\nnoop\nnoop\nnoop\nnoop\nnoop\naddx -36\nnoop\naddx 1\naddx 7\nnoop\nnoop\nnoop\naddx 2\naddx 6\nnoop\nnoop\nnoop\nnoop\nnoop\naddx 1\nnoop\nnoop\naddx 7\naddx 1\nnoop\naddx -13\naddx 13\naddx 7\nnoop\naddx 1\naddx -33\nnoop\nnoop\nnoop\naddx 2\nnoop\nnoop\nnoop\naddx 8\nnoop\naddx -1\naddx 2\naddx 1\nnoop\naddx 17\naddx -9\naddx 1\naddx 1\naddx -3\naddx 11\nnoop\nnoop\naddx 1\nnoop\naddx 1\nnoop\nnoop\naddx -13\naddx -19\naddx 1\naddx 3\naddx 26\naddx -30\naddx 12\naddx -1\naddx 3\naddx 1\nnoop\nnoop\nnoop\naddx -9\naddx 18\naddx 1\naddx 2\nnoop\nnoop\naddx 9\nnoop\nnoop\nnoop\naddx -1\naddx 2\naddx -37\naddx 1\naddx 3\nnoop\naddx 15\naddx -21\naddx 22\naddx -6\naddx 1\nnoop\naddx 2\naddx 1\nnoop\naddx -10\nnoop\nnoop\naddx 20\naddx 1\naddx 2\naddx 2\naddx -6\naddx -11\nnoop\nnoop\nnoop"
      val actual = solve(input)
      "should solve part 1" in {
        actual._1.toInt shouldBe 13140
      }

      "should solve part 2" ignore {
        actual._2 shouldBe """##..##..##..##..##..##..##..##..##..##..
                             |###...###...###...###...###...###...###.
                             |####....####....####....####....####....
                             |#####.....#####.....#####.....#####.....
                             |######......######......######......####
                             |#######.......#######.......#######.....""".stripMargin
      }
    }

    "given my input" should {
      val input = resource
      val actual = solve(input)
      "should solve part 1" in {
        actual._1.toInt shouldBe 11820
      }

      "should solve part 2" ignore {
        actual._2 shouldBe """####.###....##.###..###..#..#..##..#..#.
                             |#....#..#....#.#..#.#..#.#.#..#..#.#..#.
                             |###..#..#....#.###..#..#.##...#..#.####.
                             |#....###.....#.#..#.###..#.#..####.#..#.
                             |#....#....#..#.#..#.#.#..#.#..#..#.#..#.
                             |####.#.....##..###..#..#.#..#.#..#.#..#.""".stripMargin
      }
    }

  }
}
