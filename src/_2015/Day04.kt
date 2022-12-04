package _2015

import md5
import readInput
import java.lang.Exception

fun main() {
    runTests()
    val input = readInput("2015", "day04")[0]
    println(solution(input, "00000"))
    println(solution(input, "000000"))
}

private fun solution(input: String, prefix: String): Int {
    var number = 0
    while (true) {
        val md5 = "$input$number".md5()
        if (md5.startsWith(prefix)) {
            return number
        }
        number++
        if (number == Int.MAX_VALUE) throw Exception("the number is already too big, are you sure your code is right?")
    }
}

// tests
private fun runTests() {
    val testInput = readInput("2015", "day04_tests")
    val part1Test1 = solution(testInput[0], "00000")
    check(part1Test1 == 609043) {
        "test 1a failed, expected (609043) obtained ($part1Test1)"
    }

    val part1Test2 = solution(testInput[1], "00000")
    check(part1Test2 == 1048970) {
        "test 1b failed, expected (1048970) obtained ($part1Test2)"
    }
}
