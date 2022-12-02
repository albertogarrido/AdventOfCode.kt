import java.lang.IllegalArgumentException

fun main() {
    val input = readInput("day02")
    println(part1(input))
    println(part2(input))
}

// part 1
fun part1(input: List<String>): Int {
    var points = 0
    input.forEach { round ->
        val (opponent, player) = round.split(" ")
        points += symbolUsagePoints(player)
        points += determineRoundPoints(opponent, player)
    }
    return points
}
fun determineRoundPoints(opponent: String, player: String): Int {
    val isDraw = (player == Player.rock && opponent == Opponent.rock)
            || (player == Player.paper && opponent == Opponent.paper)
            || (player == Player.scissors && opponent == Opponent.scissors)
    if (isDraw) return 3
    val isPlayerWinner = (player == Player.rock && opponent == Opponent.scissors)
            || (player == Player.paper && opponent == Opponent.rock)
            || (player == Player.scissors && opponent == Opponent.paper)

    return if (isPlayerWinner) 6 else 0
}

fun symbolUsagePoints(player: String) = when (player) {
    "X" -> 1
    "Y" -> 2
    "Z" -> 3
    else -> {
        throw IllegalArgumentException("player played wrong symbol")
    }
}

// part 2
fun part2(input: List<String>): Int {
    var points = 0
    input.forEach { round ->
        val (opponent, result) = round.split(" ")
        val opponentPlayed = when (opponent) {
            "A" -> Plays.Rock
            "B" -> Plays.Paper
            "C" -> Plays.Scissors
            else -> throw IllegalArgumentException("played the wrong symbol")
        }
        val resultNeeded = when (result){
            "X" -> Result.Lose
            "Y" -> Result.Draw
            "Z" -> Result.Win
            else -> throw IllegalArgumentException("wrong result")
        }
        points += calculateStrategy(opponentPlayed, resultNeeded)
    }
    return points
}
fun calculateStrategy(opponentPlays: Plays, resultNeeded: Result): Int {
    val points: Int = when(resultNeeded) {
        Result.Win -> {
            6 + when(opponentPlays) {
                Plays.Rock -> 2
                Plays.Paper -> 3
                Plays.Scissors -> 1
            }
        }
        Result.Draw -> {
            3 + when(opponentPlays) {
                Plays.Rock -> 1
                Plays.Paper -> 2
                Plays.Scissors -> 3
            }
        }
        Result.Lose -> {
            when(opponentPlays) {
                Plays.Rock -> 3
                Plays.Paper -> 1
                Plays.Scissors -> 2
            }
        }
    }
    return points
}

//second part
enum class Result {
    Win, Draw, Lose
}
//second part
enum class Plays {
    Rock, Paper, Scissors
}

//first part
object Player {
    val rock = "X"
    val paper = "Y"
    val scissors = "Z"
}

//first part
object Opponent {
    val rock: String = "A"
    val paper: String = "B"
    val scissors: String = "C"
}
