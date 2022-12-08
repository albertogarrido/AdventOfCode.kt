fun main() {


    val forrest = Array(6) { i -> Array(5) { j -> 0 } }

    val xSize = forrest.size
    println("x $xSize")
    val ySize = forrest[0].size
    println("y $ySize")

    forrest.forEach {
        it.forEach { item ->
            print("$item ")
        }
        println()
    }

}

