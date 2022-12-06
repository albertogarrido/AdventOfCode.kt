package _2022

import print
import readInput

data class Instruction(
    val move: Int,
    val from: Int,
    val to: Int
)

fun main() {
//    runTests()
    runScenario(readInput("2022", "day05"))
}

private fun runScenario(input: List<String>) {
    println(part1(input))
//    println(part2(input))
}

private fun part1(input: List<String>): Int {
    input.take(9).print(indexed = true)

    var crates = HashMap<Int, Array<Char>>()
    val instructions = mutableListOf<Instruction>()

    var instructionsStarted = false

    input.take(9).forEachIndexed { index, line ->
        instructionsStarted = line.extractDigits() == "123456789"
        if (instructionsStarted) {

        } else {
            val elements = line.length / 4 + 1
            (1..elements).forEach {
//                crates[it] = crates[it].
            }
        }
    }

    return input.size
}

private fun String.extractDigits() = replace("\\D+".toRegex(), "")

private fun part2(input: List<String>): Int {
    return input.size
}

private fun runTests() {
    var passed = true

    runCatching {
        part1(readInput("2022", "day05_test_p1")).also {
            check(it == 2) {
                "part 1: expected 2, obtained $it"
            }
        }
    }.onFailure {
        passed = false
        System.err.println("[test failed] ${it.message}")
    }

    runCatching {
        part2(readInput("2022", "day05_test_p2")).also {
            check(it == 4) {
                "part 2: expected 4, obtained $it"
            }
        }
    }.onFailure {
        passed = false
        System.err.println("[test failed] ${it.message}")
    }

    if (passed) println("\u001B[32m>> all tests passed <<\u001B[0m")
}