package _2015

import readInput

fun main() {
    runTests()
    val input = readInput("2015", "day02")
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    var total = 0
    input.forEach { dimen ->
        total += part1Count(dimen)
    }
    return total
}


private fun part1Count(dimen: String): Int {
    val (l, w, h) = dimen.split("x").map { it.toInt() }
    val sidesAreas = listOf(
        2 * l * w,
        2 * w * h,
        2 * h * l
    )
    return sidesAreas.sum() + (sidesAreas.min() / 2)
}

private fun part2(input: List<String>): Int {
    var total = 0
    input.forEach { dimen ->
        total += part2Count(dimen)
    }
    return total
}

private fun part2Count(dimen: String): Int {
    val (l, w, h) = dimen.split("x").map { it.toInt() }
    val perimeters = listOf(
        (2 * l) + (2 * w),
        (2 * w) + (2 * h),
        (2 * l) + (2 * h)
    )
    return perimeters.min() + l * w * h
}

// tests
private fun runTests() {
    val testInput1a = readInput("2015", "day02_test1a")
    val part1Test1a = part1(testInput1a)
    check(part1Test1a == 58) {
        "test 1a failed, expected (58) obtained ($part1Test1a)"
    }

    val testInput1b = readInput("2015", "day02_test1b")
    val part1Test1b = part1(testInput1b)
    check(part1Test1b == 43) {
        "test 1b failed, expected (43) obtained ($part1Test1b)"
    }

    val testInput2a = readInput("2015", "day02_test1a")
    val part1Test2a = part2(testInput2a)
    check(part1Test2a == 34) {
        "test 2a failed, expected (34) obtained ($part1Test2a)"
    }

    val testInput2b = readInput("2015", "day02_test1b")
    val part1Test2b = part2(testInput2b)
    check(part1Test2b == 14) {
        "test 2b failed, expected (14) obtained ($part1Test2b)"
    }
}
