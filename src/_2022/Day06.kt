package _2022

import print
import readInput

fun main() {
    runTests()
    runScenario(readInput("2022", "day06"))
}

private fun runScenario(input: List<String>) {
    input.forEach {
        println(part1(it))
        println(part2(it))
    }
}

private fun part1(message: String): Int {
    var processedCharacters = ""
    message.forEach { char ->
        processedCharacters += char
        if (processedCharacters.length >= 4
            && processedCharacters.takeLast(4).allDifferent()
        ) {
            return processedCharacters.length
        }
    }
    return 0
}

private fun String.allDifferent(): Boolean {
    forEach { char ->
        if(this.count { it == char } > 1) {
            return false
        }
    }
    return true
}

private fun part2(message: String): Int {
    var processedCharacters = ""
    message.forEach { char ->
        processedCharacters += char
        if (processedCharacters.length >= 14
            && processedCharacters.takeLast(14).allDifferent()
        ) {
            return processedCharacters.length
        }
    }
    return 0
}

private fun runTests() {
    var passed = true

    runCatching {
        val input = readInput("2022", "day06_test_p1")
        input.forEachIndexed { index, message ->
            when (index) {
                0 -> testPart1(message, 5, index)
                1 -> testPart1(message, 6, index)
                2 -> testPart1(message, 10, index)
                3 -> testPart1(message, 11, index)
            }
        }
    }.onFailure {
        passed = false
        System.err.println("[test failed] ${it.message}")
    }

    runCatching {
        val input = readInput("2022", "day06_test_p2")
        input.forEachIndexed { index, message ->
            when (index) {
                0 -> testPart2(message, 19, index)
                1 -> testPart2(message, 23, index)
                2 -> testPart2(message, 23, index)
                3 -> testPart2(message, 29, index)
                4 -> testPart2(message, 26, index)
            }
        }
    }.onFailure {
        passed = false
        System.err.println("[test failed] ${it.message}")
    }

    if (passed) println("\u001B[32m>> all tests passed <<\u001B[0m")
}

fun testPart1(message: String, expected: Int, index: Int) {
    part1(message).also {
        check(it == expected) {
            "part 1.$index: expected $expected, obtained $it"
        }
    }
}
fun testPart2(message: String, expected: Int, index: Int) {
    part2(message).also {
        check(it == expected) {
            "part 2.$index: expected $expected, obtained $it"
        }
    }
}