fun main() {
    runTests(readInput("day0X_test"))
    runScenario(readInput("daX01"))
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

private fun runTests(testInput: List<String>) {
    check(part1(testInput) == 1)
}
