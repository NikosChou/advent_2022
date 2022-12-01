package de.choustoulakis.advent2022

trait Puzzle[I, O]:
  val day: Int

  def solve(input: I): O
