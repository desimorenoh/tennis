package game

data class Point(val value: Int) : Comparable<Point> {

    fun prettyPrint(): String {
        return when (value) {
            0 -> LOVE
            1 -> FIFTEEN
            2 -> THIRTY
            3 -> FORTY
            else -> FORTY
        }
    }

    fun enoughToWinAGame(): Boolean {
        return value >= REQUIRED_POINT_TO_WIN
    }

    fun haveEnoughAdvantageFrom(other: Point): Boolean {
        return value >= other.value + 2
    }

    fun isTied(other: Point): Boolean {
        return value == other.value
    }

    fun areEnoughForDeuce(): Boolean {
        return value >= REQUIRED_POINT_TO_WIN - 1
    }

    fun isAdvantageOver(other: Point): Boolean {
        return value == other.value + 1
    }

    override fun compareTo(other: Point): Int {
        return this.value compareTo other.value
    }

    operator fun inc() : Point {
        return Point(value + 1)
    }

    companion object {
        private const val REQUIRED_POINT_TO_WIN = 4
    }
}

data class TennisGame(
    private var player1Points: Point = Point(0),
    private var player2Points: Point = Point(0)
) {

    fun wonPoint(winner: String) {
        if (winner == PLAYER_ONE) {
            player1Points++
        } else if (winner == PLAYER_TWO) {
            player2Points++
        }
    }

    fun getScore(): String {
        val playerOneScore = player1Points.prettyPrint()
        val playerTwoScore = player2Points.prettyPrint()

        return "$playerOneScore - $playerTwoScore"
    }

    fun hasWinTheGame(): Boolean {
        return player1Points.enoughToWinAGame() && player1Points.haveEnoughAdvantageFrom(player2Points) ||
                player2Points.enoughToWinAGame() && player2Points.haveEnoughAdvantageFrom(player1Points)
    }

    fun getWinner(): String = "Player ${getWinningPlayer()} wins"

    fun getWinningPlayer(): String {
        return if (player1Points > player2Points) {
            "1"
        } else {
            "2"
        }
    }

    fun isDeuce(): Boolean {
        return player1Points.isTied(player2Points) && player1Points.areEnoughForDeuce() && player2Points.areEnoughForDeuce()
    }

    fun isAdvantage(): Boolean {
        return player1Points.areEnoughForDeuce() && player2Points.areEnoughForDeuce()
                && (player1Points.isAdvantageOver(player2Points) || player2Points.isAdvantageOver(player1Points))
    }

    fun getResult(): String {
        return when {
            isDeuce() -> "Deuce"
            isAdvantage() -> "Advantage player ${getWinningPlayer()}"
            hasWinTheGame() -> getWinner()
            else -> getScore()
        }
    }
}

private const val PLAYER_ONE = "player1"
private const val PLAYER_TWO = "player2"
private const val LOVE = "Love"
private const val FIFTEEN = "Fifteen"
private const val THIRTY = "Thirty"
private const val FORTY = "Forty"
