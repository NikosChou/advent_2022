package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.Puzzle
import de.choustoulakis.advent2022.Puzzle.IntOutput
import de.choustoulakis.advent2022.days.Day2.*
import de.choustoulakis.advent2022.days.Day2.Strategies.*

trait Day2 extends Puzzle[IntOutput] :
  override def solve(in: String): IntOutput =
    def calculateScore(strategy: Strategy): Int = in.split(Puzzle.NEW_LINE)
      .map(ar => (Shape(ar.charAt(0)), ar.charAt(2)))
      .map((opponent, me) => Round(opponent, strategy(me, opponent)))
      .map(_.score)
      .sum

    new IntOutput(calculateScore(new SimpleStrategy), calculateScore(new Part2Strategy))


object Day2:
  case class Round(opponent: Shape, me: Shape):
    val score: Int = me.points + ((me.wins(opponent), opponent.wins(me)) match
      case (true, _) => 6
      case (false, true) => 0
      case _ => 3)

  sealed trait Shape:
    def points: Int

    def wins(s: Shape): Boolean

  object Shape:
    val SHAPES: List[Shape] = List(Rock, Paper, Scissors)

    def apply(char: Char): Shape = char match
      case 'A' => Rock
      case 'B' => Paper
      case 'C' => Scissors

  object Rock extends Shape :
    override val points: Int = 1

    override def wins(s: Shape): Boolean = s match
      case Scissors => true
      case _ => false

  object Paper extends Shape :
    override val points: Int = 2

    override def wins(s: Shape): Boolean = s match
      case Rock => true
      case _ => false

  object Scissors extends Shape :
    override val points: Int = 3

    override def wins(s: Shape): Boolean = s match
      case Paper => true
      case _ => false

  object Strategies:
    sealed trait Strategy:
      def apply(in: Char, opp: Shape): Shape

    class SimpleStrategy extends Strategy :
      def apply(in: Char, s: Shape): Shape = in match
        case 'X' => Rock
        case 'Y' => Paper
        case 'Z' => Scissors

    class Part2Strategy extends Strategy :
      def apply(in: Char, opp: Shape): Shape = in match
        case 'X' => Shape.SHAPES.filter(s => opp.wins(s)).head
        case 'Y' => opp
        case 'Z' => Shape.SHAPES.filter(s => s.wins(opp)).head
