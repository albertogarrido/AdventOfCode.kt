package _2015

import readInput
import java.lang.IllegalArgumentException

fun main() {
    fun part1(input: List<String>): Int {
        val instructions = input[0]
        var floor = 0
        instructions.forEach { instruction ->
            when (instruction) {
                '(' -> floor++
                ')' -> floor--
                else -> throw IllegalArgumentException("Wrong instruction")
            }
        }
        return floor
    }

    fun part2(input: List<String>): Int {
        val instructions = input[0]
        var floor = 0
        var steps = 0
        instructions.forEach { instruction ->
            steps++
            when (instruction) {
                '(' -> {
                    floor++
                    if(floor == -1) return steps
                }
                ')' -> {
                    floor--
                    if(floor == -1) return steps
                }
                else -> throw IllegalArgumentException("Wrong instruction")
            }
        }
        return -1
    }

    val input = readInput("2015", "day01")
    println(part1(input))
    println(part2(input))
}
