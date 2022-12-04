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
        println(separator)
    }
}
