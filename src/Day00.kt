fun main() {
    runTests()
    runScenario(readInput("xxxx", "day0X"))
}

private fun runScenario(input: List<String>) {
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    return input.size
}

private fun part2(input: List<String>): Int {
    return input.size
}

private fun runTests() {

    part1(readInput("2022", "day0X_test_p1")).also {
        check(it == 1) {
            "expected X, obtained $it"
        }
    }

    part1(readInput("2022", "day0X_test_p2")).also {
        check(it == 1) {
            "expected X, obtained $it"
        }
    }

    println(">> all tests passed <<")
}