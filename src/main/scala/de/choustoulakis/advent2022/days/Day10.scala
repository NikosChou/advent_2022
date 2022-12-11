package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.Puzzle
import de.choustoulakis.advent2022.Puzzle.StringOutput
import de.choustoulakis.advent2022.days.Day10.{AddX, Info, Noop}

import scala.annotation.tailrec


trait Day10 extends Puzzle[StringOutput] :

  override def solve(in: String): StringOutput =
    val result = in.split(Puzzle.NEW_LINE)
      .flatMap {
        case s"addx $num" => List(AddX(0), AddX(num.toInt))
        case _ => List(Noop)
      }.foldLeft(Info()) { (acc, cmd) =>
      acc match
        case Info(currentCycle, _, _, _, _) =>
          val resAcc = if (currentCycle == 20 || (currentCycle - 20) % 40 == 0) {
            val newSS = acc.currentCycle * acc.x
            acc.copy(signalStrength = newSS, sum = acc.sum + newSS)
          }
          else acc
          val startOfSprite = acc.x
          val pixel = if ((0 to 2).map(_ + startOfSprite)
            .contains(acc.currentCycle % 40)) "#" else "."
          resAcc match
            case Info(c, x, ss, s, im) => cmd match
              case Noop => Info(c + 1, x, ss, s, im.concat(pixel))
              case AddX(toAdd) => Info(c + 1, x + toAdd, ss, s, im.concat(pixel))
    }

    val image = result.image.grouped(40).map(_.mkString).mkString("\n")
    StringOutput(String.valueOf(result.sum), image)

object Day10:
  case class Info(currentCycle: Int = 1, x: Int = 1, signalStrength: Int = 0, sum: Int = 0, image: String = "")

  sealed trait Command:
    val cyclesNeeded: Int

    def calculate(x: Int): Int

  case object Noop extends Command :
    override val cyclesNeeded: Int = 1

    override def calculate(x: Int): Int = x

  case class AddX(toAdd: Int) extends Command :
    override val cyclesNeeded: Int = 2

    override def calculate(x: Int): Int = x + toAdd
