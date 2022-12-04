fun main() {
    val list = listOf(
        "xyxy",
        "aabcdefgaa",
        "aaax"
    )
    list.forEachIndexed { idx, s ->
        if (s.pairsNotOverlapping()) {
            println("YES: $s")
        } else {
            println("NOPE: $s")
        }
    }
}

private fun String.pairsNotOverlapping(): Boolean {
    chunked(2).forEachIndexed { idx, s ->
        println("$this vs $s in index $idx --- matches ${countMatches(this, s)}")
        println("---------------")
    }
    return false
}

fun countMatches(string: String, pattern: String): Int {
    return string.split(pattern)
        .dropLastWhile { it.isEmpty() }
        .toTypedArray().size - 1
}

//private fun String.alternateLetters(): Boolean {
//    forEachIndexed { i, c ->
//        if (i < length - 2 && c == get(i + 2)) {
//            return true
//        }
//    }
//    return false
//}