package game

import java.util.*

class Controller(
    private val game: TennisGame
) {
    fun execute() {
        val scanner = Scanner(System.`in`)
        while (true) {
            println("Enter player name to score a point or 'exit' to quit")
            val input = scanner.nextLine()
            if (input == "exit") {
                break
            }
            game.wonPoint(input)
            println(game.getScore())
            if (game.hasWinTheGame()) {
                println(game.getWinner())
                break
            }
        }
    }
}