package de.choustoulakis.advent2022

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.nio.file.{Files, Paths}
import scala.io.Source
import scala.util.Try

class MagicalEnergySpec extends AnyWordSpec with Matchers {
  self: Puzzle[_, _] =>
  lazy val resource: String = Source.fromResource(s"days/day${self.day}").mkString
}
