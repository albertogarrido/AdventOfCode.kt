package _2015

import _2015.CardinalDirection.*
import readInput
import java.lang.IllegalArgumentException

fun main() {
    part1Tests(readInput("2015", "day03_test"))
    part2Tests(readInput("2015", "day03_test_2"))
    runScenario(readInput("2015", "day03"))
}

private fun runScenario(input: List<String>) {
    println(part1(input[0]))
    println(part2(input[0]))
}

private fun part1(input: String): Int {
    var currentLocation = Point.initial()
    val houseGifts = HashMap<Point, Int>()
    houseGifts[currentLocation] = 1

    var dirCount = 0
    do {
        val nextDirection = input[dirCount]
        currentLocation = when (CardinalDirection.fromValue(nextDirection)) {
            N -> Point(currentLocation.x, currentLocation.y + 1)
            E -> Point(currentLocation.x + 1, currentLocation.y)
            S -> Point(currentLocation.x, currentLocation.y - 1)
            W -> Point(currentLocation.x - 1, currentLocation.y)
        }

        try {
            var gifts = houseGifts[currentLocation]
            houseGifts[currentLocation] = if (gifts != null) {
                ++gifts
            } else {
                1
            }
        } catch (_: NoSuchElementException) {
            houseGifts[currentLocation] = 1
        }
        dirCount++
    } while (dirCount < input.length)
    return houseGifts.size
}

private fun part2(input: String): Int {
    var santaLocation = Point.initial()
    var roboSantaLocation = Point.initial()
    val houseGifts = HashMap<Point, Int>()
    houseGifts[santaLocation] = 1
    houseGifts[santaLocation] = 2

    input.chunked(2).forEach { coupleDirections ->

        val santaDirection = CardinalDirection.fromValue(coupleDirections[0])
        val roboSantaDirection = CardinalDirection.fromValue(coupleDirections[1])

        santaLocation = when (santaDirection) {
            N -> Point(santaLocation.x, santaLocation.y + 1)
            E -> Point(santaLocation.x + 1, santaLocation.y)
            S -> Point(santaLocation.x, santaLocation.y - 1)
            W -> Point(santaLocation.x - 1, santaLocation.y)
        }

        roboSantaLocation = when (roboSantaDirection) {
            N -> Point(roboSantaLocation.x, roboSantaLocation.y + 1)
            E -> Point(roboSantaLocation.x + 1, roboSantaLocation.y)
            S -> Point(roboSantaLocation.x, roboSantaLocation.y - 1)
            W -> Point(roboSantaLocation.x - 1, roboSantaLocation.y)
        }

        try {
            var gifts = houseGifts[santaLocation]
            houseGifts[santaLocation] = if (gifts != null) {
                ++gifts
            } else {
                1
            }
        } catch (_: NoSuchElementException) {
            houseGifts[santaLocation] = 1
        }

        try {
            var gifts = houseGifts[roboSantaLocation]
            houseGifts[roboSantaLocation] = if (gifts != null) {
                ++gifts
            } else {
                1
            }
        } catch (_: NoSuchElementException) {
            houseGifts[roboSantaLocation] = 1
        }
    }

    return houseGifts.size
}


private data class Point(val x: Int, val y: Int) {
    companion object {
        fun initial() = Point(0, 0)
    }
}

private enum class CardinalDirection {
    N, E, S, W;

    companion object {
        fun fromValue(value: Char): CardinalDirection = when (value) {
            '^' -> N
            '>' -> E
            'v' -> S
            '<' -> W
            else -> throw IllegalArgumentException("$value is not a valid cardinal point")
        }
    }
}

private fun runTests(testInput: List<String>) {
    part1Tests(testInput)
    part2Tests(testInput)
}

fun part1Tests(testInput: List<String>) {
    val result1 = part1(testInput[0])
    check(result1 == 2) { "Part 1: expected 2, obtained $result1" }
    val result2 = part1(testInput[1])
    check(result2 == 4) { "Part 1: expected 4, obtained $result2" }
    val result3 = part1(testInput[2])
    check(result3 == 2) { "Part 1: expected 2, obtained $result3" }
}

fun part2Tests(testInput: List<String>) {
    val result1 = part2(testInput[0])
    check(result1 == 3) { "a Part 2: expected 3, obtained $result1" }
    val result2 = part2(testInput[1])
    check(result2 == 3) { "b Part 2: expected 3, obtained $result2" }
    val result3 = part2(testInput[2])
    check(result3 == 11) { "c Part 2: expected 11, obtained $result3" }
}
