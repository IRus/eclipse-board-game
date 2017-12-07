package com.scyterrating.helpers

import com.scyterrating.models.GameResult
import com.scyterrating.models.User
import ma.com.BaseUnitTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RatingCalculatorUnitTest() : BaseUnitTest() {

    @Test
    @Throws(Exception::class)
    fun isValid_duplicatedUsers_returnFalse() {
        val game: MutableList<GameResult> = ArrayList()
        game.add(GameResult(User(ANDREW), 30.0))
        game.add(GameResult(User(ANDREW), 35.0))
        assertFalse(RatingCalculator.isValid(game))
    }

    @Test
    @Throws(Exception::class)
    fun isValid_correctUsers_returnTrue() {
        val game: MutableList<GameResult> = ArrayList()
        game.add(GameResult(User(ANDREW), 30.0))
        game.add(GameResult(User(GLEB), 35.0))
        game.add(GameResult(User(RUSLAN), 44.0))
        assertTrue(RatingCalculator.isValid(game))
    }

    @Test
    @Throws(Exception::class)
    fun getAverage_sameValues_value() {
        val game: MutableList<GameResult> = ArrayList()
        val result = 35.0
        game.add(GameResult(User(ANDREW), result))
        game.add(GameResult(User(GLEB), result))
        game.add(GameResult(User(RUSLAN), result))
        assertEquals(result, RatingCalculator.getAverage(game), RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun getAverage_nearValues_value() {
        val game: MutableList<GameResult> = ArrayList()
        game.add(GameResult(User(ANDREW), 33.0))
        game.add(GameResult(User(GLEB), 34.0))
        game.add(GameResult(User(RUSLAN), 35.0))
        assertEquals(34.0, RatingCalculator.getAverage(game), RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun getAverage_differentValues_value() {
        assertEquals(32.3333, RatingCalculator.getAverage(getDefaultGame()), RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun getGameRate_zeroToFiveGame_returnFive() {
        assertEquals(10, RatingCalculator.getPlayerRate(0))
        assertEquals(5, RatingCalculator.getPlayerRate(1))
        assertEquals(5, RatingCalculator.getPlayerRate(2))
        assertEquals(4, RatingCalculator.getPlayerRate(3))
        assertEquals(3, RatingCalculator.getPlayerRate(4))
    }

    @Test
    @Throws(Exception::class)
    fun getGameRate_sixToTenGame_returnTwo() {
        assertEquals(2, RatingCalculator.getPlayerRate(5))
        assertEquals(2, RatingCalculator.getPlayerRate(6))
        assertEquals(2, RatingCalculator.getPlayerRate(7))
        assertEquals(2, RatingCalculator.getPlayerRate(8))
        assertEquals(2, RatingCalculator.getPlayerRate(9))
    }

    @Test
    @Throws(Exception::class)
    fun getGameRate_elevenAndMoreGame_returnOne() {
        assertEquals(1, RatingCalculator.getPlayerRate(10))
        assertEquals(1, RatingCalculator.getPlayerRate(11))
        assertEquals(1, RatingCalculator.getPlayerRate(20))
        assertEquals(1, RatingCalculator.getPlayerRate(30))
        assertEquals(1, RatingCalculator.getPlayerRate(1000))
    }

    @Test
    @Throws(Exception::class)
    fun gameResultsRate() {
        assertEquals(1.5, RatingCalculator.getGameResultsRate(6, 1), 0.0)
        assertEquals(1.3, RatingCalculator.getGameResultsRate(6, 2), 0.0)
        assertEquals(1.1, RatingCalculator.getGameResultsRate(6, 3), 0.0)
        assertEquals(1.0, RatingCalculator.getGameResultsRate(6, 4), 0.0)
        assertEquals(1.0, RatingCalculator.getGameResultsRate(6, 5), 0.0)
        assertEquals(1.0, RatingCalculator.getGameResultsRate(6, 6), 0.0)
        assertEquals(1.4, RatingCalculator.getGameResultsRate(5, 1), 0.0)
        assertEquals(1.2, RatingCalculator.getGameResultsRate(5, 2), 0.0)
        assertEquals(1.1, RatingCalculator.getGameResultsRate(5, 3), 0.0)
        assertEquals(1.0, RatingCalculator.getGameResultsRate(5, 4), 0.0)
        assertEquals(1.0, RatingCalculator.getGameResultsRate(5, 5), 0.0)
        assertEquals(1.3, RatingCalculator.getGameResultsRate(4, 1), 0.0)
        assertEquals(1.1, RatingCalculator.getGameResultsRate(4, 2), 0.0)
        assertEquals(1.0, RatingCalculator.getGameResultsRate(4, 3), 0.0)
        assertEquals(1.0, RatingCalculator.getGameResultsRate(4, 4), 0.0)
        assertEquals(1.1, RatingCalculator.getGameResultsRate(3, 1), 0.0)
        assertEquals(1.0, RatingCalculator.getGameResultsRate(3, 2), 0.0)
        assertEquals(1.0, RatingCalculator.getGameResultsRate(3, 3), 0.0)
        assertEquals(0.5, RatingCalculator.getGameResultsRate(2, 1), 0.0)
        assertEquals(0.5, RatingCalculator.getGameResultsRate(2, 2), 0.0)
    }


    @Test
    @Throws(Exception::class)
    fun otherPlayerAverage() {
        var game: MutableList<GameResult> = getDefaultGame()
        assertEquals(1000.0, RatingCalculator.otherPlayerAverageRating(game[0].user, game), RATING_DELTA)
        game = getRandomGame()
        assertEquals(1150.0, RatingCalculator.otherPlayerAverageRating(game[0].user, game), RATING_DELTA)
        assertEquals(850.0, RatingCalculator.otherPlayerAverageRating(game[1].user, game), RATING_DELTA)
        assertEquals(700.0, RatingCalculator.otherPlayerAverageRating(game[2].user, game), RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun expectedPointsRate() {
        var game: MutableList<GameResult> = getDefaultGame()
        assertEquals(1.0, RatingCalculator.expectedPointsRate(game[0].user, game), RATING_DELTA)
        game = getRandomGame()
        assertEquals(0.30196, RatingCalculator.expectedPointsRate(game[0].user, game), RATING_DELTA)
        assertEquals(1.1709, RatingCalculator.expectedPointsRate(game[1].user, game), RATING_DELTA)
        assertEquals(1.5984, RatingCalculator.expectedPointsRate(game[2].user, game), RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun getUserPlace() {
        var game: MutableList<GameResult> = getDefaultGame()
        assertEquals(3, RatingCalculator.getUserPlace(game[0], game))
        assertEquals(1, RatingCalculator.getUserPlace(game[1], game))
        assertEquals(2, RatingCalculator.getUserPlace(game[2], game))
        game = getRandomGame2()
        assertEquals(1, RatingCalculator.getUserPlace(game[0], game))
        assertEquals(1, RatingCalculator.getUserPlace(game[1], game))
        assertEquals(3, RatingCalculator.getUserPlace(game[2], game))
    }

    @Test
    @Throws(Exception::class)
    fun getGameResult() {
        assertEquals(1.0, RatingCalculator.getGameResult(6, 1), RATING_DELTA)
        assertEquals(0.8, RatingCalculator.getGameResult(6, 2), RATING_DELTA)
        assertEquals(0.6, RatingCalculator.getGameResult(6, 3), RATING_DELTA)
        assertEquals(0.4, RatingCalculator.getGameResult(6, 4), RATING_DELTA)
        assertEquals(0.2, RatingCalculator.getGameResult(6, 5), RATING_DELTA)
        assertEquals(0.0, RatingCalculator.getGameResult(6, 6), RATING_DELTA)

        assertEquals(1.0, RatingCalculator.getGameResult(2, 1), 0.0)
        assertEquals(0.0, RatingCalculator.getGameResult(2, 2), 0.0)
        assertEquals(1.0, RatingCalculator.getGameResult(3, 1), 0.0)
        assertEquals(0.5, RatingCalculator.getGameResult(3, 2), 0.0)
        assertEquals(0.0, RatingCalculator.getGameResult(3, 3), 0.0)
        assertEquals(1.0, RatingCalculator.getGameResult(4, 1), RATING_DELTA)
        assertEquals(0.666667, RatingCalculator.getGameResult(4, 2), RATING_DELTA)
        assertEquals(0.333333, RatingCalculator.getGameResult(4, 3), RATING_DELTA)
        assertEquals(0.0, RatingCalculator.getGameResult(4, 4), RATING_DELTA)
        assertEquals(1.0, RatingCalculator.getGameResult(5, 1), RATING_DELTA)
        assertEquals(0.75, RatingCalculator.getGameResult(5, 2), RATING_DELTA)
        assertEquals(0.5, RatingCalculator.getGameResult(5, 3), RATING_DELTA)
        assertEquals(0.25, RatingCalculator.getGameResult(5, 4), RATING_DELTA)
        assertEquals(0.0, RatingCalculator.getGameResult(5, 5), RATING_DELTA)



        assertEquals(1.0, RatingCalculator.getGameResult(7, 1), RATING_DELTA)
        assertEquals(0.8333333, RatingCalculator.getGameResult(7, 2), RATING_DELTA)
        assertEquals(0.6666667, RatingCalculator.getGameResult(7, 3), RATING_DELTA)
        assertEquals(0.5, RatingCalculator.getGameResult(7, 4), RATING_DELTA)
        assertEquals(0.3333333, RatingCalculator.getGameResult(7, 5), RATING_DELTA)
        assertEquals(0.1666667, RatingCalculator.getGameResult(7, 6), RATING_DELTA)
        assertEquals(0.0, RatingCalculator.getGameResult(7, 7), RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun updateRating() {
        val game1: MutableList<GameResult> = ArrayList()
        val user1: User = User(ANDREW, 0, 400.0)
        val user2: User = User(GLEB, 0, 400.0)
        val user3: User = User(RUSLAN, 0, 400.0)
        game1.add(GameResult(user1, 10.0))
        game1.add(GameResult(user2, 50.0))
        game1.add(GameResult(user3, 37.0))
        assertEquals(32.3333, RatingCalculator.getAverage(game1), RATING_DELTA)
        RatingCalculator.updateRating(game1)
        assertEquals(192.7835, user1.updatedRating, RATING_DELTA)
        assertEquals(580.3092, user2.updatedRating, RATING_DELTA)
        assertEquals(443.2989, user3.updatedRating, RATING_DELTA)

        val game2: MutableList<GameResult> = ArrayList()
        game2.add(GameResult(user1, 10.0))
        game2.add(GameResult(user2, 50.0))
        game2.add(GameResult(user3, 37.0))
        assertEquals(32.3333, RatingCalculator.getAverage(game1), RATING_DELTA)
        RatingCalculator.updateRating(game1)
        assertEquals(141.9175, user1.updatedRating, RATING_DELTA)
        assertEquals(622.1034, user2.updatedRating, RATING_DELTA)
        assertEquals(455.1615, user3.updatedRating, RATING_DELTA)
    }

    fun getDefaultGame() : MutableList<GameResult> {
        val game: MutableList<GameResult> = ArrayList()
        game.add(GameResult(User(ANDREW, 0, 1000.0), 10.0))
        game.add(GameResult(User(GLEB, 0, 1000.0), 50.0))
        game.add(GameResult(User(RUSLAN, 0, 1000.0), 37.0))
        return game;
    }

    fun getRandomGame() : MutableList<GameResult> {
        val game: MutableList<GameResult> = ArrayList()
        game.add(GameResult(User(ANDREW, 0, 400.0), 10.0))
        game.add(GameResult(User(GLEB, 0, 1000.0), 50.0))
        game.add(GameResult(User(RUSLAN, 0, 1300.0), 37.0))
        return game;
    }

    fun getRandomGame2() : MutableList<GameResult> {
        val game: MutableList<GameResult> = ArrayList()
        game.add(GameResult(User(ANDREW, 0, 400.0), 55.0))
        game.add(GameResult(User(GLEB, 0, 1000.0), 55.0))
        game.add(GameResult(User(RUSLAN, 0, 1300.0), 37.0))
        return game;
    }
}


