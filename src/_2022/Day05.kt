package _2022
// todo not final, clean up
import print
import readInput

fun main() {
    runTests()
    runScenario(readInput("2022", "day05"))
}

private fun runScenario(input: List<String>) {
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    val crates = parseCrates(input)
//    crates.print()
    val instructions = parseInstructions(input, startFrom = crates.size + 1)
//    instructions.print()
    return input.size
}

fun parseCrates(input: List<String>): List<String> {
    val cratesList = mutableListOf<String>()
    val sacksNumber = -1
    run breaking@{
        (input.indices).forEach {
            val line = input[it]
            if (line.isBlank()) {
                cratesList.removeLast()
                return@breaking
            }
            cratesList.add(line)
        }
    }

    val stacks = mutableListOf<MutableList<String>>()
    cratesList.reversed().forEach { line ->

    }

    return cratesList
}

fun parseInstructions(input: List<String>, startFrom: Int): List<String> {
    val instructions = mutableListOf<String>()
    (startFrom until input.size).forEach {
        instructions.add(input[it])
    }
    return instructions
}

private fun String.removeSpaces()  = replace("\\s".toRegex(), "")

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