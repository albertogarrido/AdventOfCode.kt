import java.lang.IllegalArgumentException


fun main() {
    runTests(readInput("day03_test"))
    runScenario(readInput("day03"))
}

fun runScenario(input: List<String>) {
    val rucksacks = input.map {
        Pair(it.substring(0, (it.length / 2)), it.substring(it.length / 2, it.length))
    }
    println(part1(rucksacks))
    println(part2(input))
}

private fun part1(input: List<Pair<String, String>>) = input.sumOf { rucksack ->
    rucksack.first.first { it in rucksack.second }.priorityValue()
}

private fun part2(input: List<String>) = input.chunked(3).sumOf { group ->
    group[0].first { it in group[1] && it in group[2] }.priorityValue()
}

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

fun runTests(testInput: List<String>) {
    val testRucksacks = testInput.map {
        Pair(it.substring(0, (it.length / 2)), it.substring(it.length / 2, it.length))
    }
    val result = part1(testRucksacks)
    check(result == 157) {
        "expected 157, obtained $result"
    }
    val result2 = part2(testInput)
    check(result2 == 70) {
        "expected 70, obtained $result"
    }
}