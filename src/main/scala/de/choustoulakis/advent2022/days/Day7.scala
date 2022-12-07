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

    def accumulatePaths(node: Node): mutable.ArrayBuffer[Node] = node match
      case _: File => mutable.ArrayBuffer()
      case _ => node.children.foldLeft(mutable.ArrayBuffer[Node]()) { (acc, n) =>
        acc.:++(accumulatePaths(n))
      }.:+(node)


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
  private def findRootFrom(node: Node): Path = if (node.parent != null) findRootFrom(node.parent) else node.asInstanceOf[Path]

  private def createTreeStructure(in: String): Node =
    in.split(Puzzle.NEW_LINE)
      .foldLeft[Path](Path("/", mutable.ArrayBuffer(), null)) { (workingDirectory, termInOut) =>
        termInOut match
          case s"$$ $command" => command match
            case s"cd .." => workingDirectory.parent
            case s"cd /" => findRootFrom(workingDirectory)
            case s"cd $dir" => workingDirectory.children.flatMap {
              case path: Path if path.name == dir => Some(path)
              case _ => None
            }.head
            case "ls" => workingDirectory
          case output =>
            workingDirectory.children.+=(Node.apply(output, workingDirectory))
            workingDirectory
      }

object Day7:
  abstract class Node:
    def size: Int

    def name: String

    def children: mutable.ArrayBuffer[Node]

    def parent: Path


  object Node:
    def apply(output: String, parent: Path): Node = output match
      case s"$size $name" if Try(size.toInt).isSuccess => File(name, size.toInt, parent)
      case s"dir $dir" => Path(dir, mutable.ArrayBuffer(), parent)

  case class Path(name: String, children: mutable.ArrayBuffer[Node], parent: Path) extends Node :
    override def size: Int = children.map(_.size).sum

  case class File(name: String, fileSize: Int, parent: Path) extends Node :
    override def children: mutable.ArrayBuffer[Node] = mutable.ArrayBuffer()

    override def size: Int = fileSize