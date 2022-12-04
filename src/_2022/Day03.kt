package _2022

import readInput
import java.lang.IllegalArgumentException

fun main() {
    runTests(readInput("2022", "day03_test"))
    runScenario(readInput("2022", "day03"))
}

private fun runScenario(input: List<String>) {
    val rucksacks = input.map { buildRucksacks(it) }
    println(part1(rucksacks))
    println(part2(input))
}

private fun part1(input: List<Pair<String, String>>) = input.sumOf { rucksack ->
    rucksack.first.first { it in rucksack.second }.priorityValue()
}

private fun part2(input: List<String>) = input.chunked(3).sumOf { group ->
    group[0].first { it in group[1] && it in group[2] }.priorityValue()
}

private fun buildRucksacks(input: String) =
    Pair(input.substring(0, input.length / 2), input.substring(input.length / 2, input.length))

/**
 *Lowercase item types a through z have priorities 1 through 26.
 *Uppercase item types A through Z have priorities 27 through 52.
 */
private fun Char.priorityValue(): Int {
    if (!isLetter()) throw IllegalArgumentException("not a letter")
    return if (isUpperCase()) {
        code - 38
    } else {
        code - 96
    }
}

private fun runTests(testInput: List<String>) {
    val testRucksacks = testInput.map { buildRucksacks(it) }
    val result = part1(testRucksacks)
    check(result == 157) {
        "expected 157, obtained $result"
    }
    val result2 = part2(testInput)
    check(result2 == 70) {
        "expected 70, obtained $result2"
    }
}
