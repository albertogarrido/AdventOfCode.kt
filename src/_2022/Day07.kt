package _2022

import readInput

fun main() {
    runTests()
    runScenario(readInput("2022", "day07"))
}

private fun runScenario(input: List<String>) {
    println("Result Part 1: ${part1(input)}")
    println("Result Part 2: ${part2(input)}")
}

private fun part1(input: List<String>): Int {
    val dirTree: HashMap<String, Int> = parseDirectoryTree(input)
    var total = 0
    dirTree.forEach {
        if (it.key != "/") {
            if (it.value < 100000) {
                total += it.value
            }
        }
    }
    return total
}

private fun part2(input: List<String>): Int {
    val totalSpace = 70000000
    val spaceNeeded = 30000000
    val dirTree: HashMap<String, Int> = parseDirectoryTree(input)
    val currentFreeSpace = totalSpace - dirTree["/"]!!
    val spaceToFree = spaceNeeded - currentFreeSpace
    val deletionCandidates = HashMap<String, Int>()
    dirTree.forEach {
        if (it.value >= spaceToFree) {
            deletionCandidates[it.key] = it.value
        }
    }
    return deletionCandidates.minBy { it.value }.value
}

fun parseDirectoryTree(input: List<String>): HashMap<String, Int> {
    val hashMap: HashMap<String, Int> = HashMap()
    var currentPath = ""
    input.forEach { line ->
        val lineDetails = line.split(" ")
        when {
            isCommand(lineDetails[0]) -> {
                if (lineDetails[1] == "cd" && lineDetails[2] != "..") {
                    currentPath += if (lineDetails[2] == "/") {
                        lineDetails[2]
                    } else {
                        "${lineDetails[2]}/"
                    }
                    if (!hashMap.containsKey(currentPath)) {
                        hashMap[currentPath] = 0
                    }
                } else if (lineDetails[1] == "cd" && lineDetails[2] == "..") {
                    currentPath = removeLastDir(currentPath)
                }
            }

            isFile(lineDetails[0]) -> {
                hashMap[currentPath] = hashMap[currentPath]!!.plus(lineDetails[0].toInt())
                var tempPath = currentPath
                repeat((currentPath.split('/').size - 2 downTo 0).count()) {
                    tempPath = removeLastDir(tempPath)
                    if (hashMap.containsKey(tempPath)) {
                        hashMap[tempPath] = hashMap[tempPath]!!.plus(lineDetails[0].toInt())
                    }
                }
            }
        }
    }
    return hashMap
}

fun isFile(s: String) = s.toIntOrNull() != null

fun isCommand(s: String) = s == "$"

fun removeLastDir(currentPath: String): String {
    var path = currentPath
    path = path.removeRange(path.lastIndex, path.lastIndex + 1)
    val index = path.lastIndexOf("/")
    path = path.removeRange(index + 1, path.lastIndex + 1)
    return path
}

private fun runTests() {
    var passed = true

    runCatching {
        part1(readInput("2022", "day07_test_p1")).also {
            check(it == 95437) {
                "part 1: expected 95437, obtained $it"
            }
        }
    }.onFailure {
        passed = false
        System.err.println("[test 1 failed] ${it.message}")
    }.onSuccess {
        println("\u001B[32m>> [test 1 success] <<\u001B[0m")
    }

    runCatching {
        part2(readInput("2022", "day07_test_p1")).also {
            check(it == 24933642) {
                "part 2: expected 24933642, obtained $it"
            }
        }
    }.onFailure {
        passed = false
        System.err.println("[test failed] ${it.message}")
    }.onSuccess {
        println("\u001B[32m>> [test 2 success] <<\u001B[0m")
    }

    runCatching {
        part1(readInput("2022", "day07")).also {
            check(it == 1792222) {
                "Final Result Part 1: expected 1792222, obtained $it"
            }
        }
    }.onFailure {
        passed = false
        System.err.println("[test final result 1 failed] ${it.message}")
    }.onSuccess {
        println("\u001B[32m>> [test final result 1 success] <<\u001B[0m")
    }

    runCatching {
        part2(readInput("2022", "day07")).also {
            check(it == 1112963) {
                "Final Result Part 1: expected 1112963, obtained $it"
            }
        }
    }.onFailure {
        passed = false
        System.err.println("[test final result 2 failed] ${it.message}")
    }.onSuccess {
        println("\u001B[32m>> [test final result 2 success] <<\u001B[0m")
    }

    if (passed) println("\u001B[32m>> all tests passed <<\u001B[0m")
}
