package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.Puzzle

import scala.collection.mutable

trait Day6 extends Puzzle[String, (Int, Int)] :
  override def solve(in: String): (Int, Int) =
    println(in.distinct.mkString)
    val readPackets: Int => Int = positionMarker => in.zipWithIndex.drop(3)
      .find(t => in.take(t._2).takeRight(positionMarker).distinct.length == positionMarker)
      .map(_._2).get

    (readPackets(4), readPackets(14))