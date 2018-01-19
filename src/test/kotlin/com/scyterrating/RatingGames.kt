package com.scyterrating

import by.ibragimov.eclipse.game.Player
import by.ibragimov.eclipse.game.PlayerResult
import com.ma.scyterrating.ScyterRatingHelper
import ma.com.BaseUnitTest
import org.junit.Test


class RatingGames() : BaseUnitTest() {

    @Test
    fun games() {
        val Andrew = Player(ANDREW, 5, 1000.0)
        val Gleb = Player(GLEB, 5, 1000.0)
        val Ruslan = Player(RUSLAN, 5, 1000.0)
        val Artur = Player(ARTUR, 5, 1000.0)
        val Anna = Player(ANNA, 5, 1000.0)
        val Vlad = Player(VLAD, Player.DEFAULT_GAMES, 700.0)
        val Ilya = Player(ILYA, Player.DEFAULT_GAMES, 700.0)
        val Bronislav = Player(BRONISLAV, Player.DEFAULT_GAMES, 700.0)

        val players = listOf(
                Andrew, Gleb, Ruslan, Artur, Anna, Vlad, Ilya, Bronislav
        )

        val calc = ScyterRatingHelper()

        val game1 = listOf(
                PlayerResult(Ruslan, 29.0),
                PlayerResult(Vlad, 32.0),
                PlayerResult(Artur, 19.0),
                PlayerResult(Andrew, 39.0),
                PlayerResult(Gleb, 40.0)
        )
        calc.gamePlayed(game1)

        val game2 = listOf(
                PlayerResult(Ruslan, 41.0),
                PlayerResult(Artur, 54.0),
                PlayerResult(Gleb, 68.0)
        )
        calc.gamePlayed(game2)

        val game3 = listOf(
                PlayerResult(Anna, 28.0),
                PlayerResult(Andrew, 50.0),
                PlayerResult(Ruslan, 46.0),
                PlayerResult(Ilya, 29.0)
        )
        calc.gamePlayed(game3)

        val game4 = listOf(
                PlayerResult(Ilya, 35.0),
                PlayerResult(Andrew, 59.0),
                PlayerResult(Ruslan, 0.0),
                PlayerResult(Bronislav, 55.0)
        )
        calc.gamePlayed(game4)

        val game5 = listOf(
                PlayerResult(Andrew, 51.0),
                PlayerResult(Ruslan, 17.0),
                PlayerResult(Artur, 32.0),
                PlayerResult(Gleb, 45.0)
        )
        calc.gamePlayed(game5)

        players.forEach { println(it.rating) }
    }
}