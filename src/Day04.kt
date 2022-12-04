fun main() {
    runTests()
//    runScenario(readInput("day04"))
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
    var passed = true

    runCatching {
        part1(readInput("day0X_test_p1")).also {
            check(it == 1) {
                "part 1: expected X, obtained $it"
            }
        }
    }.onFailure {
        passed = false
        System.err.println("[test failed] ${it.message}")
    }

    runCatching {
        part2(readInput("day0X_test_p2")).also {
            check(it == 1) {
                "part 2: expected X, obtained $it"
            }
        }
    }.onFailure {
        passed = false
        System.err.println("[test failed] ${it.message}")
    }

    if (passed) println("\u001B[32m>> all tests passed <<\u001B[0m")
}

