package _2022

import readInput

fun main() {
    fun getCaloriesPerElf(input: List<String>): Array<Int> {
        val calories = Array(input.count { it == "" } + 2) { 0 }
        var elf = 1
        input.forEach {
            if (it == "") {
                elf++
            } else {
                calories[elf] = calories[elf] + it.toInt()
            }
        }
        return calories
    }

    fun part1(input: List<String>): Int {
        val calories = getCaloriesPerElf(input)
        return calories.max()
    }

    fun part2(input: List<String>): Int {
        val calories = getCaloriesPerElf(input).also { array -> array.sortBy { it } }
        var sum = 0
        calories.lastIndex.let { idx ->
            (idx downTo idx - 2).forEach {
                sum += calories[it]
            }
        }
        return sum
    }

    val input = readInput("2022", "day01")

    println("part1: ${part1(input)}")
    println("part2: ${part2(input)}")
}
