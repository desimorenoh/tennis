package game

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TennisGameTest {

    @Test
    fun `player should start with no points`() {
        val game = TennisGame()

        val result = game.getScore()

        assertEquals("Love - Love", result)
    }

    @Test
    fun `player one scores`() {
        val game = TennisGame()

        game.wonPoint("player1")
        val result = game.getScore()

        assertEquals("Fifteen - Love", result)

    }

    @Test
    fun `player two scores`() {
        val game = TennisGame()

        game.wonPoint("player2")
        val result = game.getScore()

        assertEquals("Love - Fifteen", result)

    }

    @Test
    fun `both players scores`() {
        val game = TennisGame()

        game.wonPoint("player1")
        game.wonPoint("player2")
        val result = game.getScore()

        assertEquals("Fifteen - Fifteen", result)
    }

    @Test
    fun `player one scores a second time`() {
        val game = TennisGame()

        game.wonPoint("player1")
        game.wonPoint("player1")
        val result = game.getScore()

        assertEquals("Thirty - Love", result)
    }

    @Test
    fun `player two scores a second time`() {
        val game = TennisGame()

        game.wonPoint("player2")
        game.wonPoint("player2")
        val result = game.getScore()

        assertEquals("Love - Thirty", result)
    }

    @Test
    fun `player one scores a third time`() {
        val game = TennisGame()

        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player1")
        val result = game.getScore()

        assertEquals("Forty - Love", result)
    }

    @Test
    fun `player one won the game when player 2 has no points`() {
        val game = TennisGame()

        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player1")
        val result = game.hasWinTheGame()

        assertEquals(true, result)
    }

    @Test
    fun `player one won the game when player 2 has two points`() {
        val game = TennisGame()

        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player2")
        game.wonPoint("player2")
        val result = game.hasWinTheGame()

        assertEquals(true, result)
    }

    @Test
    fun `return false when no one won the game`() {
        val game = TennisGame()

        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player2")
        game.wonPoint("player2")
        game.wonPoint("player2")
        game.wonPoint("player2")
        val result = game.hasWinTheGame()

        assertEquals(false, result)
    }

    @Test
    fun `is Deuce when 2 players has at least 3 points, and the score is equal`() {
        val game = TennisGame()

        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player2")
        game.wonPoint("player2")
        game.wonPoint("player2")
        val result = game.isDeuce()

        assertEquals(true, result)
    }

    @Test
    fun `is Advantage when 2 players has at least 3 points, and player1 has one more point than his opponent`() {
        val game = TennisGame()

        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player2")
        game.wonPoint("player2")
        game.wonPoint("player2")
        val result = game.isAdvantage()

        assertEquals(true, result)
    }

    @Test
    fun `is Advantage when 2 players has at least 3 points, and player2 has one more point than his opponent`() {
        val game = TennisGame()

        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player2")
        game.wonPoint("player2")
        game.wonPoint("player2")
        game.wonPoint("player2")
        val result = game.isAdvantage()

        assertEquals(true, result)
    }

    @Test
    fun `return game result`() {
        var game = TennisGame()

        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player2")
        game.wonPoint("player2")
        game.wonPoint("player2")

        assertEquals("Deuce", game.getResult())

        game = TennisGame()

        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player2")
        game.wonPoint("player2")
        game.wonPoint("player2")

        assertEquals("Advantage player 1", game.getResult())

        game = TennisGame()

        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player1")
        game.wonPoint("player2")
        game.wonPoint("player2")

        assertEquals("Player 1 wins", game.getResult())

        game = TennisGame()

        game.wonPoint("player1")
        game.wonPoint("player2")
        game.wonPoint("player2")

        assertEquals("Fifteen - Thirty", game.getResult())

    }

    @Test
    fun `are equals`() {
        val game = TennisGame()
        val game2 = TennisGame()

        assertEquals(game, game2)

        game.wonPoint("player2")
        game2.wonPoint("player2")

        assertEquals(game, game2)

        game.getScore()
        assertEquals(game, game2)

        val point = Point(1)
        point.prettyPrint()

    }

}
