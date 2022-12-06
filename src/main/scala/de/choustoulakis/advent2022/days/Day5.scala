package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.Puzzle
import de.choustoulakis.advent2022.days.Day5.{Command, mapCrates}

import scala.collection.mutable

trait Day5 extends Puzzle[String, (String, String)] :
  override def solve(in: String): (String, String) =
    val (crates_in, commands_in) = in.splitAt(in.indexOf("\n\n"))
    val crates = mapCrates(crates_in)
    val commands = commands_in.split(Puzzle.NEW_LINE).filter(_.nonEmpty).map(Command.apply)

    commands.foreach { c =>
      (1 to c.move).foreach { times =>
        crates(c.to - 1).stack.push(crates(c.from - 1).stack.pop())
      }
    }

    val part1 = crates.map(_.stack.pop()).map(_.char).mkString

    val part2Crates = mapCrates(crates_in)
    commands.foreach { c =>
      if (c.move <= 1) {
        part2Crates(c.to - 1).stack.push(part2Crates(c.from - 1).stack.pop())
      } else {
        val toMove = (1 to c.move).map(_ => part2Crates(c.from - 1).stack.pop()).reverse
        toMove.foreach(e => part2Crates(c.to - 1).stack.push(e))
      }
    }
    val part2 = part2Crates.map(_.stack.pop()).map(_.char).mkString
    (part1, part2)

object Day5:

  case class Command(move: Int, from: Int, to: Int)

  object Command:
    def apply(in: String): Command = in match
      case s"move $move from $from to $to" => Command(move.toInt, from.toInt, to.toInt)

  import scala.collection.mutable.Stack

  case class Crate(char: Char)

  object Crate:
    private val CRATE_PATTERN = "(\\[[A-Z]\\])".r

    def apply(in: String): Option[Crate] = in.trim match
      case CRATE_PATTERN(c) => Option(Crate(c.charAt(1)))
      case _ => None

  case class CrateStack(id: Char, stack: mutable.Stack[Crate])

  def mapCrates(in: String): List[CrateStack] =
    val lines = in.split(Puzzle.NEW_LINE).reverse
    val crates = lines.head.split("\\s\\s")
      .map(nums => CrateStack(nums(1), mutable.Stack()))
      .toList

    lines.tail.foreach { row =>
      row.grouped(4).zipWithIndex.map(t => (t._2, Crate.apply(t._1)))
        .foreach(t => t._2.map { cr =>
          crates(t._1).stack.push(cr)
        })
    }
    crates
