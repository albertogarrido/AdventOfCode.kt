fun main() {
    val string = "12345678"
    val count = string.count { it == 'a' }
    println(string.takeLast(4))
}

//private fun String.alternateLetters(): Boolean {
//    forEachIndexed { i, c ->
//        if (i < length - 2 && c == get(i + 2)) {
//            return true
//        }
//    }
//    return false
//}