package com.scyterrating

import com.scyterrating.helpers.PrinterHelper
import com.scyterrating.helpers.RatingCalculator
import com.scyterrating.models.GameResult
import com.scyterrating.models.User
import ma.com.BaseUnitTest
import org.junit.Assert
import org.junit.Test


class RatingGames() : BaseUnitTest() {

    @Test
    @Throws(Exception::class)
    fun games() {
        val Andrew: User = User(ANDREW, 5, 1000.0)
        val Gleb: User = User(GLEB, 5, 1000.0)
        val Ruslan: User = User(RUSLAN, 5, 1000.0)
        val Artur: User = User(ARTUR, 5, 1000.0)
        val Anna: User = User(ANNA, 5, 1000.0)
        val Vlad: User = User(VLAD, User.DEFAULT_GAMES, 700.0)
        val Ilya: User = User(ILYA, User.DEFAULT_GAMES, 700.0)
        val Bronislav: User = User (BRONISLAV, User.DEFAULT_GAMES, 700.0)

        val players: MutableList<User> = ArrayList()
        players.add(Andrew)
        players.add(Gleb)
        players.add(Ruslan)
        players.add(Artur)
        players.add(Anna)
        players.add(Vlad)
        players.add(Ilya)
        players.add(Bronislav)


        val game1: MutableList<GameResult> = ArrayList()
        game1.add(GameResult(Ruslan, 29.0))
        game1.add(GameResult(Vlad, 32.0))
        game1.add(GameResult(Artur, 19.0))
        game1.add(GameResult(Andrew, 39.0))
        game1.add(GameResult(Gleb, 40.0))
        Assert.assertEquals(31.8, RatingCalculator.getAverage(game1), RATING_DELTA)
        RatingCalculator.updateRating(game1)

        PrinterHelper.printRating(players)

        val game2: MutableList<GameResult> = ArrayList()
        game2.add(GameResult(Ruslan, 41.0))
        game2.add(GameResult(Artur, 54.0))
        game2.add(GameResult(Gleb, 68.0))
        Assert.assertEquals(54.3333, RatingCalculator.getAverage(game2), RATING_DELTA)
        RatingCalculator.updateRating(game2)

        PrinterHelper.printRating(players)

        val game3: MutableList<GameResult> = ArrayList()
        game3.add(GameResult(Anna, 28.0))
        game3.add(GameResult(Andrew, 50.0))
        game3.add(GameResult(Ruslan, 46.0))
        game3.add(GameResult(Ilya, 29.0))
        Assert.assertEquals(38.25, RatingCalculator.getAverage(game3), RATING_DELTA)
        RatingCalculator.updateRating(game3)

        PrinterHelper.printRating(players)

        val game4: MutableList<GameResult> = ArrayList()
        game4.add(GameResult(Ilya, 35.0))
        game4.add(GameResult(Andrew, 59.0))
        game4.add(GameResult(Ruslan, 0.0))
        game4.add(GameResult(Bronislav, 55.0))
        Assert.assertEquals(37.25, RatingCalculator.getAverage(game4), RATING_DELTA)
        RatingCalculator.updateRating(game4)

        val game5:MutableList<GameResult> = ArrayList()
        game5.add(GameResult(Andrew, 51.0))
        game5.add(GameResult(Ruslan, 17.0))
        game5.add(GameResult(Artur, 32.0))
        game5.add(GameResult(Gleb, 45.0))
        RatingCalculator.updateRating(game5)

        PrinterHelper.printRating(players)
    }
}