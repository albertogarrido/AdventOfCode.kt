package _2022

import printError
import printSuccess
import printlnSuccess
import readInput
import java.lang.IndexOutOfBoundsException
import kotlin.io.print
import kotlin.io.println

fun main() {
    runTests()
    runScenario(readInput("2022", "day08"))
}

private fun runScenario(input: List<String>) {
    val forrest = parseForest(input)
    println("Result Part 1: ${part1(forrest, false)}")
    println("Result Part 2: ${part2(forrest)}")
}

private fun part1(forrest: Array<IntArray>, print: Boolean = false): Int {
    var visibleTrees = (forrest.size - 2 + forrest[0].size) * 2

    forrest.forEachIndexed { i, row ->
        if (i > 0 && i < row.lastIndex) {
            row.forEachIndexed { k, tree ->
                if (k > 0 && k < forrest[0].lastIndex) {
                    var blockedUp = false
                    var blockedDown = false
                    var blockedLeft = false
                    var blockedRight = false
                    (0 until i).forEach { treeIndex ->
                        if (forrest[treeIndex][k] >= tree) {
                            blockedUp = true
                        }
                    }
                    (row.lastIndex downTo i + 1).forEach { treeIndex ->
                        if (forrest[treeIndex][k] >= tree) {
                            blockedDown = true
                        }
                    }
                    (0 until k).forEach { treeIndex ->
                        if (forrest[i][treeIndex] >= tree) {
                            blockedLeft = true
                        }
                    }
                    ((forrest.size - 1) downTo k + 1).forEach { treeIndex ->
                        if (forrest[i][treeIndex] >= tree) {
                            blockedRight = true
                        }
                    }

                    if (!blockedUp || !blockedDown || !blockedLeft || !blockedRight) {
                        visibleTrees++
                        if (print) printError(tree.toString())
                    } else {
                        if (print) printSuccess(tree.toString())
                    }
                } else {
                    if (print) printError(tree.toString())
                }
                if (print) print("")
            }
        } else {
            if (print) {
                row.forEach { tree ->
                    printError("$tree ")
                }
            }
        }
        if (print) println()
    }
    return visibleTrees
}

private fun part2(forrest: Array<IntArray>): Int {
    val scores = mutableListOf<Int>()
    forrest.forEachIndexed { i, row ->
        if (i > 0 && i < row.lastIndex) {
            row.forEachIndexed { k, tree ->
                if (k > 0 && k < forrest[0].lastIndex) {
                    var north = 0
                    var east = 0
                    var south = 0
                    var west = 0

                    var clearView = true
                    var northDirection = i - 1
                    while (clearView) {
                        try {
                            val nextTree = forrest[northDirection][k]
                            north++
                            if (nextTree >= tree) {
                                clearView = false
                            }
                            northDirection--
                        } catch (iob: IndexOutOfBoundsException) {
                            clearView = false
                        }
                    }

                    clearView = true
                    var southDirection = i + 1
                    while (clearView) {
                        try {
                            val nextTree = forrest[southDirection][k]
                            south++
                            if (nextTree >= tree) {
                                clearView = false
                            }
                            southDirection++
                        } catch (iob: IndexOutOfBoundsException) {
                            clearView = false
                        }
                    }

                    clearView = true
                    var westDirection = k - 1
                    while (clearView) {
                        try {
                            val nextTree = forrest[i][westDirection]
                            west++
                            if (nextTree >= tree) {
                                clearView = false
                            }
                            westDirection--
                        } catch (iob: IndexOutOfBoundsException) {
                            clearView = false
                        }
                    }

                    clearView = true
                    var eastDirection = k + 1
                    while (clearView) {
                        try {
                            val nextTree = forrest[i][eastDirection]
                            east++
                            if (nextTree >= tree) {
                                clearView = false
                            }
                            eastDirection++
                        } catch (iob: IndexOutOfBoundsException) {
                            clearView = false
                        }
                    }
                    scores.add(north * east * south * west)
                }
            }
        }
    }
    return scores.max()
}

private fun parseForest(input: List<String>): Array<IntArray> {
    val forrest = Array(input[0].length) { IntArray(input.size) }
    input.forEachIndexed { i, line ->
        line.forEachIndexed { k, tree ->
            forrest[i][k] = tree.code - 48 //ASCII code of 0
        }
    }
    return forrest
}

private fun runTests() {
    var passed = true

    runCatching {
        val forrest = parseForest(readInput("2022", "day08_test_p1"))
        part1(forrest, print = false).also {
            check(it == 21) {
                "part 1: expected 21, obtained $it"
            }
        }
    }.onFailure {
        passed = false
        System.err.println("[test failed] ${it.message}")
    }.onSuccess {
        printlnSuccess("[ test part 1 passed (result $it) ]")
    }

    runCatching {
        val forrest = parseForest(readInput("2022", "day08_test_p2"))
        part2(forrest).also {
            check(it == 8) {
                "part 2: expected 8, obtained $it"
            }
        }
    }.onFailure {
        passed = false
        System.err.println("[test failed] ${it.message}")
    }.onSuccess {
        printlnSuccess("[ test part 2 passed (result $it) ]")
    }

    if (passed) printlnSuccess(">> all tests passed <<")
}