package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.Puzzle
import de.choustoulakis.advent2022.Puzzle.{IntOutput, LongOutput, StringOutput}
import de.choustoulakis.advent2022.days.Day11.Monkey

import scala.annotation.tailrec
import scala.util.Try


trait Day11 extends Puzzle[LongOutput] :

  override def solve(in: String): LongOutput =

    val businessLevels = playFor(in, 20)
    println(businessLevels)
    val part1 = businessLevels.max * businessLevels.sorted.takeRight(2).head

    val businessLevels2 = playFor(in, 10000, true)
    val part2 = businessLevels2.max * businessLevels2.sorted.takeRight(2).head


    LongOutput(part1, part2)

  private def playFor(in: String, rounds: Int, part2: Boolean = false): List[Long] =
    val reliefFn: Long => Long = if(part2) {
      val product = in.split(Puzzle.NEW_LINE).filter(_.contains("Test: divisible")).map { case s"  Test: divisible by $d" => d.toInt }.product
      _ % product
    } else _ / 3
    val monkeys = in.split(Puzzle.NEW_LINE * 2)
      .map(toMonkey).toList
    (1 to rounds).foreach { _ =>
      monkeys.indices.foreach { i =>
        val m = monkeys(i)
        m.items.indices.foreach { _ =>
          m.num = m.num + 1
          val head = reliefFn(m.op(m.items.dequeue()))
          val toM = m.throwFn(head)
          monkeys(toM).items.enqueue(head)
        }
      }
    }
    monkeys.map(m => m.num)

  private def toMonkey(in: String): Monkey =
    val id = in.split(Puzzle.NEW_LINE)(0) match
      case s"Monkey $i:" => i.toInt
    val items = in.split(Puzzle.NEW_LINE)(1) match
      case s"  Starting items: $itemList" => collection.mutable.Queue(itemList.split(",").map(_.trim).map(_.toLong).toList: _*)
    val op = mapOperation(in.split(Puzzle.NEW_LINE)(2))
    val divider = in.split(Puzzle.NEW_LINE)(3) match
      case s"  Test: divisible by $d" => d.toInt
    val ifTrue = in.split(Puzzle.NEW_LINE)(4) match
      case s"    If true: throw to monkey $i" => i.toInt
    val ifFalse = in.split(Puzzle.NEW_LINE)(5) match
      case s"    If false: throw to monkey $i" => i.toInt
    Monkey(id, items, op, l => if (l % divider == 0) ifTrue else ifFalse)

  private def mapOperation(in: String): Long => Long = in match
    case s"  Operation: new = old + $num" => old
      => old + Try(num.toLong).getOrElse(old)
    case s"  Operation: new = old * $num" => old
      => old * Try(num.toLong).getOrElse(old)

object Day11:
  case class Monkey(id: Int, items: collection.mutable.Queue[Long], op: Long => Long, throwFn: Long => Int, var num: Long = 0)