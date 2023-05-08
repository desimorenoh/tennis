import game.Controller
import game.TennisGame

fun main(args: Array<String>) {
    val game = Controller(TennisGame())

    game.execute()

}