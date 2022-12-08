import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(year: String, name: String) = File("src/_$year/inputs", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun <E> Iterable<E>.print(
    indexed: Boolean = false,
    prefix: String = "",
    suffix: String = "",
    separator: String = ""
) {
    forEachIndexed { index, element ->
        if (indexed) {
            print("[$index]")
        }
        println("$prefix$element$suffix")
        if(separator.isNotBlank()) {
            println(separator)
        }
    }
}

const val ANSI_RESET = "\u001B[0m"
const val ANSI_BLACK = "\u001B[30m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_YELLOW = "\u001B[33m"
const val ANSI_BLUE = "\u001B[34m"
const val ANSI_PURPLE = "\u001B[35m"
const val ANSI_CYAN = "\u001B[36m"
const val ANSI_WHITE = "\u001B[37m"

fun printlnSuccess(content: String) {
    println("$ANSI_GREEN$content$ANSI_RESET")
}
fun printSuccess(content: String) {
    print("$ANSI_GREEN$content$ANSI_RESET")
}
fun printError(content: String) {
    print("$ANSI_RED$content$ANSI_RESET")
}