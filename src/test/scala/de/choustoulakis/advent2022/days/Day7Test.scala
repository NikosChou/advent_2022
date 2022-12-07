package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.MagicalEnergySpec
import org.scalatest.*

import scala.io.Source

class Day7Test extends MagicalEnergySpec with Day7 {
  "--- Day 7: No Space Left On Device ---" when {
    "given test input" should {
      val input = "$ cd /\n$ ls\ndir a\n14848514 b.txt\n8504156 c.dat\ndir d\n$ cd a\n$ ls\ndir e\n29116 f\n2557 g\n62596 h.lst\n$ cd e\n$ ls\n584 i\n$ cd ..\n$ cd ..\n$ cd d\n$ ls\n4060174 j\n8033020 d.log\n5626152 d.ext\n7214296 k"
      val actual = solve(input)
      "should solve part 1" in {
        actual._1 shouldBe 95437
      }

      "should solve part 2" in {
        actual._2 shouldBe 24933642
      }
    }

    "given my input" should {
      val input = resource
      val actual = solve(input)
      "should solve part 1" in  {
        actual._1 shouldBe 1642503
      }

      "should solve part 2" in {
        actual._2 shouldBe 6999588
      }
    }

  }
}
