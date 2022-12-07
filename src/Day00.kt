fun main() {
    runTests()
    runScenario(readInput("xxxx", "day0X"))
}

private fun runScenario(input: List<String>) {
    println("Result Part 1: ${part1(input)}")
    println("Result Part 2: ${part2(input)}")
}

private fun part1(input: List<String>): Int {
    return input.size
}

private fun part2(input: List<String>): Int {
    return input.size
}

private fun runTests() {
    var passed = true

    runCatching {
        part1(readInput("xxxx", "day0X_test_p1")).also {
            check(it == 2) {
                "part 1: expected 2, obtained $it"
            }
        }
    }.onFailure {
        passed = false
        System.err.println("[test failed] ${it.message}")
    }

    runCatching {
        part2(readInput("xxxx", "day0x_test_p2")).also {
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