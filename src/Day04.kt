fun main() {
    runTests()
    runScenario(readInput("day04"))
}

private fun runScenario(input: List<String>) {
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    var redundancies = 0
    parseAssignments(input).forEach {
        if(containsRange(it.first, it.second)) {
            redundancies++
        }
    }
    return redundancies
}

private fun part2(input: List<String>): Int {
    var redundancies = 0
    parseAssignments(input).forEach {
        if(containsAnyOfTheRange(it.first, it.second)) {
            redundancies++
        }
    }
    return redundancies
}

private fun containsRange(rangeA: IntRange, rangeB: IntRange): Boolean {
    if(rangeA.first >= rangeB.first && rangeA.last <= rangeB.last) {
        return true
    }
    if(rangeB.first >= rangeA.first && rangeB.last <= rangeA.last) {
        return true
    }
    return false
}

private fun containsAnyOfTheRange(rangeA: IntRange, rangeB: IntRange): Boolean {
    rangeA.forEach {
        if(rangeB.contains(it)) {
            return true
        }
    }
    rangeB.forEach {
        if(rangeA.contains(it)) {
            return true
        }
    }
    return false
}

private fun parseAssignments(input: List<String>) = input.map { items ->
    val row = items.split(",", "-").map { it.toInt() }
    Pair(row[0]..row[1], row[2]..row[3])
}

private fun runTests() {
    val testInput = readInput("day04_test_p1")
    val resultP1 = part1(testInput)
    check(resultP1 == 2) {
        "expected 2, obtained $resultP1"
    }

    val testInput2 = readInput("day04_test_p2")
    val resultP2 = part2(testInput2)
    check(resultP2 == 4) {
        "expected 4, obtained $resultP2"
    }
    println(">> all tests passed <<")
}

