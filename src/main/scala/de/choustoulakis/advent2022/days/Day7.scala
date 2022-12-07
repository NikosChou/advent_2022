package de.choustoulakis.advent2022.days

import de.choustoulakis.advent2022.Puzzle
import de.choustoulakis.advent2022.Puzzle.IntOutput
import de.choustoulakis.advent2022.days.Day7.*

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Try

trait Day7 extends Puzzle[IntOutput] :
  override def solve(in: String): IntOutput =
    val root = findRootFrom(createTreeStructure(in))

    def accumulatePaths(node: File): mutable.ArrayBuffer[File] = node match
      case file if file.isDirectory => node.children
        .foldLeft(mutable.ArrayBuffer[File]()) { (acc, n) => acc.:++(accumulatePaths(n)) }
        .:+(node)
      case _ => mutable.ArrayBuffer()


    val allDirectories = accumulatePaths(root)
    val part1 = allDirectories.filter(n => n.size <= 100000).map(_.size).sum

    val totalDiskSpace = 70000000
    val neededSpace = 30000000
    val rootSize = root.size
    val availableSpace = totalDiskSpace - rootSize
    val minSpaceNeeded = neededSpace - availableSpace
    val part2 = allDirectories.filter(_.size >= minSpaceNeeded)
      .map(d => (d, d.size - minSpaceNeeded))
      .minBy(_._2)

    new IntOutput(part1, part2._1.size)

  @tailrec
  private def findRootFrom(node: File): File = if (node.parent != null) findRootFrom(node.parent) else node

  private def createTreeStructure(in: String): File =
    in.split(Puzzle.NEW_LINE)
      .foldLeft(File("/", 0, mutable.ArrayBuffer(), null, true)) { (workingDirectory, termInOut) =>
        termInOut match
          case s"$$ $command" => command match
            case s"cd .." => workingDirectory.parent
            case s"cd /" => findRootFrom(workingDirectory)
            case s"cd $dir" => workingDirectory.children.flatMap {
              case path if path.name == dir => Some(path)
              case _ => None
            }.head
            case "ls" => workingDirectory
          case output =>
            workingDirectory.children.+=(File(output, workingDirectory))
            workingDirectory
      }

object Day7:

  case class File(name: String, fileSize: Int, children: mutable.ArrayBuffer[File], parent: File, isDirectory: Boolean):
    def size: Int = if (children.isEmpty) fileSize else children.map(_.size).sum

    override def toString: String = s"name: $name, size: $size, children: $children, isDir: $isDirectory"

  object File:
    def apply(output: String, parent: File): File = output match
      case s"$size $name" if Try(size.toInt).isSuccess => File(name, size.toInt, mutable.ArrayBuffer(), parent, false)
      case s"dir $dir" => File(dir, 0, mutable.ArrayBuffer(), parent, true)