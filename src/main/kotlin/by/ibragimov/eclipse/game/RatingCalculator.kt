package by.ibragimov.eclipse.game

import com.scyterrating.helpers.GameResult
import com.scyterrating.helpers.ScyterRatingCalculator
import com.scyterrating.helpers.User

interface RatingCalculator {
    fun calculate(game: Game): Game
}

class DefaultRatingCalculator : RatingCalculator {
    private val calc = ScyterRatingCalculator()

    override fun calculate(game: Game): Game {
        val meh = game.results.map { User(it.player.name) to it }
        val meh2 = meh.map { GameResult(it.first, it.second.score.value.toDouble()) }
        calc.updateRating(meh2)
        val results = meh.map { Result(it.second.player, Score(it.second.score.value, it.first.rating.toInt())) }
        return game.copy(results = results)
    }
}