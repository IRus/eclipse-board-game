package com.scyterrating

import com.scyterrating.helpers.GameResult
import com.scyterrating.helpers.ScyterRatingCalculator
import com.scyterrating.helpers.User
import ma.com.BaseUnitTest
import org.junit.Test


class RatingGames() : BaseUnitTest() {

    @Test
    fun games() {
        val Andrew = User(ANDREW, 5, 1000.0)
        val Gleb = User(GLEB, 5, 1000.0)
        val Ruslan = User(RUSLAN, 5, 1000.0)
        val Artur = User(ARTUR, 5, 1000.0)
        val Anna = User(ANNA, 5, 1000.0)
        val Vlad = User(VLAD, User.DEFAULT_GAMES, 700.0)
        val Ilya = User(ILYA, User.DEFAULT_GAMES, 700.0)
        val Bronislav = User(BRONISLAV, User.DEFAULT_GAMES, 700.0)

        val players = listOf(
            Andrew, Gleb, Ruslan, Artur, Anna, Vlad, Ilya, Bronislav
        )

        val calc = ScyterRatingCalculator()

        val game1 = listOf(
            GameResult(Ruslan, 29.0),
            GameResult(Vlad, 32.0),
            GameResult(Artur, 19.0),
            GameResult(Andrew, 39.0),
            GameResult(Gleb, 40.0)
        )
        calc.updateRating(game1)

        val game2 = listOf(
            GameResult(Ruslan, 41.0),
            GameResult(Artur, 54.0),
            GameResult(Gleb, 68.0)
        )
        calc.updateRating(game2)

        val game3 = listOf(
            GameResult(Anna, 28.0),
            GameResult(Andrew, 50.0),
            GameResult(Ruslan, 46.0),
            GameResult(Ilya, 29.0)
        )
        calc.updateRating(game3)

        val game4 = listOf(
            GameResult(Ilya, 35.0),
            GameResult(Andrew, 59.0),
            GameResult(Ruslan, 0.0),
            GameResult(Bronislav, 55.0)
        )
        calc.updateRating(game4)

        val game5 = listOf(
            GameResult(Andrew, 51.0),
            GameResult(Ruslan, 17.0),
            GameResult(Artur, 32.0),
            GameResult(Gleb, 45.0)
        )
        calc.updateRating(game5)

        players.forEach { println(it.rating) }
    }
}