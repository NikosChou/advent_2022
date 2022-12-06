package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.Puzzle
import de.choustoulakis.advent2022.Puzzle.IntOutput

import scala.collection.mutable

trait Day6 extends Puzzle[IntOutput] :
  override def solve(in: String): IntOutput =
    println(in.distinct.mkString)
    val readPackets: Int => Int = positionMarker => in.zipWithIndex.drop(3)
      .find(t => in.take(t._2).takeRight(positionMarker).distinct.length == positionMarker)
      .map(_._2).get

    new IntOutput(readPackets(4), readPackets(14))