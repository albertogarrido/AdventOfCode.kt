package _2015

import readInput

fun main() {
    runTests()
    runScenario(readInput("2015", "day05"))
}

private fun runScenario(input: List<String>) {
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>) =
    input.filter {
        it.vowelsCount() >= 3 && it.hasNiceLetters() && !it.isNaughty()
    }.size

private fun Char.isVowel() = this in vowels

private fun String.vowelsCount() = sumOf {
    if (it.isVowel()) 1 as Int
    else 0 as Int
}

private fun String.hasNiceLetters(): Boolean {
    forEachIndexed { index, c ->
        if (index < length - 1 && c == get(index + 1)) {
            return true
        }
    }
    return false
}

private fun String.isNaughty(): Boolean {
    naughtyLetters.forEach {
        if (this.contains(it)) return true
    }
    return false
}

private fun part2(input: List<String>) =
    input.filter {
       it.pairsNotOverlapping() && it.alternateLetters()
    }.size

private fun String.alternateLetters(): Boolean {
    forEachIndexed { i, c ->
        if (i < length - 2 && c == get(i + 2)) {
            return true
        }
    }
    return false
}

private fun String.pairsNotOverlapping(): Boolean {
    chunked(2).forEachIndexed { idx, s ->

    }
    return false
}


private val vowels by lazy { listOf('a', 'e', 'i', 'o', 'u') }
private val naughtyLetters by lazy { listOf("ab", "cd", "pq", "xy") }

private fun runTests() {
    var passed = true

    runCatching {
        part1(readInput("2015", "day05_test_p1")).also {
            check(it == 2) {
                "part 1: expected 2, obtained $it"
            }
        }
    }.onFailure {
        passed = false
        System.err.println("[test failed] ${it.message}")
    }

    runCatching {
        part2(readInput("2015", "day05_test_p2")).also {
            check(it == 2) {
                "part 2: expected 2, obtained $it"
            }
        }
    }.onFailure {
        passed = false
        System.err.println("[test failed] ${it.message}")
    }

    if (passed) println("\u001B[32m>> all tests passed <<\u001B[0m")

}
