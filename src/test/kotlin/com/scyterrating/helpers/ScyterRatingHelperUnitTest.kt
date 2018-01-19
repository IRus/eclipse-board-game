package com.scyterrating.helpers

import by.ibragimov.eclipse.game.Player
import by.ibragimov.eclipse.game.PlayerResult
import com.ma.scyterrating.ScyterRatingHelper
import ma.com.BaseUnitTest
import org.junit.Assert.*
import org.junit.Test

class ScyterRatingHelperUnitTest : BaseUnitTest() {

    @Test
    @Throws(Exception::class)
    fun isValid_duplicatedPlayers_returnFalse() {
        val game: MutableList<PlayerResult> = ArrayList()
        game.add(PlayerResult(Player(ANDREW), 30.0))
        game.add(PlayerResult(Player(ANDREW), 35.0))
        assertFalse(ScyterRatingHelper().isValid(game))
    }

    @Test
    @Throws(Exception::class)
    fun isValid_correctPlayers_returnTrue() {
        val game: MutableList<PlayerResult> = ArrayList()
        game.add(PlayerResult(Player(ANDREW), 30.0))
        game.add(PlayerResult(Player(GLEB), 35.0))
        game.add(PlayerResult(Player(RUSLAN), 44.0))
        assertTrue(ScyterRatingHelper().isValid(game))
    }

    @Test
    @Throws(Exception::class)
    fun getAverage_sameValues_value() {
        val game: MutableList<PlayerResult> = ArrayList()
        val result = 35.0
        game.add(PlayerResult(Player(ANDREW), result))
        game.add(PlayerResult(Player(GLEB), result))
        game.add(PlayerResult(Player(RUSLAN), result))
        assertEquals(result, ScyterRatingHelper().getAverage(game), RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun getAverage_nearValues_value() {
        val game: MutableList<PlayerResult> = ArrayList()
        game.add(PlayerResult(Player(ANDREW), 33.0))
        game.add(PlayerResult(Player(GLEB), 34.0))
        game.add(PlayerResult(Player(RUSLAN), 35.0))
        assertEquals(34.0, ScyterRatingHelper().getAverage(game), RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun getAverage_differentValues_value() {
        assertEquals(32.3333, ScyterRatingHelper().getAverage(getDefaultGame()), RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun getGameRate_zeroToFiveGame_returnFive() {
        assertEquals(10, ScyterRatingHelper().getPlayerRate(0))
        assertEquals(5, ScyterRatingHelper().getPlayerRate(1))
        assertEquals(5, ScyterRatingHelper().getPlayerRate(2))
        assertEquals(4, ScyterRatingHelper().getPlayerRate(3))
        assertEquals(3, ScyterRatingHelper().getPlayerRate(4))
    }

    @Test
    @Throws(Exception::class)
    fun getGameRate_sixToTenGame_returnTwo() {
        assertEquals(2, ScyterRatingHelper().getPlayerRate(5))
        assertEquals(2, ScyterRatingHelper().getPlayerRate(6))
        assertEquals(2, ScyterRatingHelper().getPlayerRate(7))
        assertEquals(2, ScyterRatingHelper().getPlayerRate(8))
        assertEquals(2, ScyterRatingHelper().getPlayerRate(9))
    }

    @Test
    @Throws(Exception::class)
    fun getGameRate_elevenAndMoreGame_returnOne() {
        assertEquals(1, ScyterRatingHelper().getPlayerRate(10))
        assertEquals(1, ScyterRatingHelper().getPlayerRate(11))
        assertEquals(1, ScyterRatingHelper().getPlayerRate(20))
        assertEquals(1, ScyterRatingHelper().getPlayerRate(30))
        assertEquals(1, ScyterRatingHelper().getPlayerRate(1000))
    }

    @Test
    @Throws(Exception::class)
    fun PlayerResultsRate() {
        assertEquals(1.5, ScyterRatingHelper().getGameResultsRate(6, 1), 0.0)
        assertEquals(1.3, ScyterRatingHelper().getGameResultsRate(6, 2), 0.0)
        assertEquals(1.1, ScyterRatingHelper().getGameResultsRate(6, 3), 0.0)
        assertEquals(1.0, ScyterRatingHelper().getGameResultsRate(6, 4), 0.0)
        assertEquals(1.0, ScyterRatingHelper().getGameResultsRate(6, 5), 0.0)
        assertEquals(1.0, ScyterRatingHelper().getGameResultsRate(6, 6), 0.0)
        assertEquals(1.4, ScyterRatingHelper().getGameResultsRate(5, 1), 0.0)
        assertEquals(1.2, ScyterRatingHelper().getGameResultsRate(5, 2), 0.0)
        assertEquals(1.1, ScyterRatingHelper().getGameResultsRate(5, 3), 0.0)
        assertEquals(1.0, ScyterRatingHelper().getGameResultsRate(5, 4), 0.0)
        assertEquals(1.0, ScyterRatingHelper().getGameResultsRate(5, 5), 0.0)
        assertEquals(1.3, ScyterRatingHelper().getGameResultsRate(4, 1), 0.0)
        assertEquals(1.1, ScyterRatingHelper().getGameResultsRate(4, 2), 0.0)
        assertEquals(1.0, ScyterRatingHelper().getGameResultsRate(4, 3), 0.0)
        assertEquals(1.0, ScyterRatingHelper().getGameResultsRate(4, 4), 0.0)
        assertEquals(1.1, ScyterRatingHelper().getGameResultsRate(3, 1), 0.0)
        assertEquals(1.0, ScyterRatingHelper().getGameResultsRate(3, 2), 0.0)
        assertEquals(1.0, ScyterRatingHelper().getGameResultsRate(3, 3), 0.0)
        assertEquals(0.5, ScyterRatingHelper().getGameResultsRate(2, 1), 0.0)
        assertEquals(0.5, ScyterRatingHelper().getGameResultsRate(2, 2), 0.0)
    }


    @Test
    @Throws(Exception::class)
    fun otherPlayerAverage() {
        var game: MutableList<PlayerResult> = getDefaultGame()
        assertEquals(1000.0, ScyterRatingHelper().otherPlayerAverageRating(game[0].player, game), RATING_DELTA)
        game = getRandomGame()
        assertEquals(1150.0, ScyterRatingHelper().otherPlayerAverageRating(game[0].player, game), RATING_DELTA)
        assertEquals(850.0, ScyterRatingHelper().otherPlayerAverageRating(game[1].player, game), RATING_DELTA)
        assertEquals(700.0, ScyterRatingHelper().otherPlayerAverageRating(game[2].player, game), RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun expectedPointsRate() {
        var game: MutableList<PlayerResult> = getDefaultGame()
        assertEquals(1.0, ScyterRatingHelper().expectedPointsRate(game[0].player, game), RATING_DELTA)
        game = getRandomGame()
        assertEquals(0.30196, ScyterRatingHelper().expectedPointsRate(game[0].player, game), RATING_DELTA)
        assertEquals(1.1709, ScyterRatingHelper().expectedPointsRate(game[1].player, game), RATING_DELTA)
        assertEquals(1.5984, ScyterRatingHelper().expectedPointsRate(game[2].player, game), RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun getPlayerPlace() {
        var game: MutableList<PlayerResult> = getDefaultGame()
        assertEquals(3, ScyterRatingHelper().getPlayerPlace(game[0], game))
        assertEquals(1, ScyterRatingHelper().getPlayerPlace(game[1], game))
        assertEquals(2, ScyterRatingHelper().getPlayerPlace(game[2], game))
        game = getRandomGame2()
        assertEquals(1, ScyterRatingHelper().getPlayerPlace(game[0], game))
        assertEquals(1, ScyterRatingHelper().getPlayerPlace(game[1], game))
        assertEquals(3, ScyterRatingHelper().getPlayerPlace(game[2], game))
    }

    @Test
    @Throws(Exception::class)
    fun getPlayerResult() {
//        assertEquals(1.0, ScyterRatingHelper().getPlayerResult(6, 1), RATING_DELTA)
//        assertEquals(0.8, ScyterRatingHelper().getPlayerResult(6, 2), RATING_DELTA)
//        assertEquals(0.6, ScyterRatingHelper().getPlayerResult(6, 3), RATING_DELTA)
//        assertEquals(0.4, ScyterRatingHelper().getPlayerResult(6, 4), RATING_DELTA)
//        assertEquals(0.2, ScyterRatingHelper().getPlayerResult(6, 5), RATING_DELTA)
//        assertEquals(0.0, ScyterRatingHelper().getPlayerResult(6, 6), RATING_DELTA)
//
//        assertEquals(1.0, ScyterRatingHelper().getPlayerResult(2, 1), 0.0)
//        assertEquals(0.0, ScyterRatingHelper().getPlayerResult(2, 2), 0.0)
//        assertEquals(1.0, ScyterRatingHelper().getPlayerResult(3, 1), 0.0)
//        assertEquals(0.5, ScyterRatingHelper().getPlayerResult(3, 2), 0.0)
//        assertEquals(0.0, ScyterRatingHelper().getPlayerResult(3, 3), 0.0)
//        assertEquals(1.0, ScyterRatingHelper().getPlayerResult(4, 1), RATING_DELTA)
//        assertEquals(0.666667, ScyterRatingHelper().getPlayerResult(4, 2), RATING_DELTA)
//        assertEquals(0.333333, ScyterRatingHelper().getPlayerResult(4, 3), RATING_DELTA)
//        assertEquals(0.0, ScyterRatingHelper().getPlayerResult(4, 4), RATING_DELTA)
//        assertEquals(1.0, ScyterRatingHelper().getPlayerResult(5, 1), RATING_DELTA)
//        assertEquals(0.75, ScyterRatingHelper().getPlayerResult(5, 2), RATING_DELTA)
//        assertEquals(0.5, ScyterRatingHelper().getPlayerResult(5, 3), RATING_DELTA)
//        assertEquals(0.25, ScyterRatingHelper().getPlayerResult(5, 4), RATING_DELTA)
//        assertEquals(0.0, ScyterRatingHelper().getPlayerResult(5, 5), RATING_DELTA)
//
//
//
//        assertEquals(1.0, ScyterRatingHelper().getPlayerResult(7, 1), RATING_DELTA)
//        assertEquals(0.8333333, ScyterRatingHelper().getPlayerResult(7, 2), RATING_DELTA)
//        assertEquals(0.6666667, ScyterRatingHelper().getPlayerResult(7, 3), RATING_DELTA)
//        assertEquals(0.5, ScyterRatingHelper().getPlayerResult(7, 4), RATING_DELTA)
//        assertEquals(0.3333333, ScyterRatingHelper().getPlayerResult(7, 5), RATING_DELTA)
//        assertEquals(0.1666667, ScyterRatingHelper().getPlayerResult(7, 6), RATING_DELTA)
//        assertEquals(0.0, ScyterRatingHelper().getPlayerResult(7, 7), RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun updateRating() {
        val calc = ScyterRatingHelper()

        val game1: MutableList<PlayerResult> = ArrayList()
        val Player1: Player = Player(ANDREW, 0, 400.0)
        val Player2: Player = Player(GLEB, 0, 400.0)
        val Player3: Player = Player(RUSLAN, 0, 400.0)
        game1.add(PlayerResult(Player1, 10.0))
        game1.add(PlayerResult(Player2, 50.0))
        game1.add(PlayerResult(Player3, 37.0))
        assertEquals(32.3333, calc.getAverage(game1), RATING_DELTA)
        calc.gamePlayed(game1)
        assertEquals(358.5567, Player1.updatedRating, RATING_DELTA)
        assertEquals(432.7835, Player2.updatedRating, RATING_DELTA)
        assertEquals(408.6597, Player3.updatedRating, RATING_DELTA)

        val game2: MutableList<PlayerResult> = ArrayList()
        game2.add(PlayerResult(Player1, 10.0))
        game2.add(PlayerResult(Player2, 50.0))
        game2.add(PlayerResult(Player3, 37.0))
        assertEquals(32.3333, calc.getAverage(game1), RATING_DELTA)
        calc.gamePlayed(game1)
        assertEquals(339.9784, Player1.updatedRating, RATING_DELTA)
        assertEquals(447.4786, Player2.updatedRating, RATING_DELTA)
        assertEquals(412.5410, Player3.updatedRating, RATING_DELTA)
    }

    fun getDefaultGame() : MutableList<PlayerResult> {
        val game: MutableList<PlayerResult> = ArrayList()
        game.add(PlayerResult(Player(ANDREW, 0, 1000.0), 10.0))
        game.add(PlayerResult(Player(GLEB, 0, 1000.0), 50.0))
        game.add(PlayerResult(Player(RUSLAN, 0, 1000.0), 37.0))
        return game;
    }

    fun getRandomGame() : MutableList<PlayerResult> {
        val game: MutableList<PlayerResult> = ArrayList()
        game.add(PlayerResult(Player(ANDREW, 0, 400.0), 10.0))
        game.add(PlayerResult(Player(GLEB, 0, 1000.0), 50.0))
        game.add(PlayerResult(Player(RUSLAN, 0, 1300.0), 37.0))
        return game;
    }

    fun getRandomGame2() : MutableList<PlayerResult> {
        val game: MutableList<PlayerResult> = ArrayList()
        game.add(PlayerResult(Player(ANDREW, 0, 400.0), 55.0))
        game.add(PlayerResult(Player(GLEB, 0, 1000.0), 55.0))
        game.add(PlayerResult(Player(RUSLAN, 0, 1300.0), 37.0))
        return game;
    }
}


