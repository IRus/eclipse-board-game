package by.ibragimov.eclipse.game.ratings

import by.ibragimov.eclipse.game.Player
import by.ibragimov.eclipse.game.PlayerRating
import by.ibragimov.eclipse.game.PlayerResult
import by.ibragimov.eclipse.game.RatingCalculator
import by.ibragimov.eclipse.game.SeasonGames
import by.ibragimov.eclipse.game.SeasonRatings

/**
 * Simple rating based on relative position of players in each game.
 *
 * @author Ibragimov Ruslan
 */
class SimpleRatingCalculator : RatingCalculator {
    override fun calculate(season: SeasonGames): SeasonRatings {
        return season.games
            .flatMap {
                gameToPairs(it.playerResults)
            }
            .groupBy { it.player }
            .map { PlayerRating(it.key, (it.value.sumBy { it.value } * 1000) / gamesPerSeason(season, it.key)) }
            .let {
                SeasonRatings(it)
            }
    }

    private fun gamesPerSeason(season: SeasonGames, player: Player): Int {
        return season.games
            .filter { it.playerResults.firstOrNull { it.player == player } != null }
            .count()
    }

    private fun gameToPairs(results: List<PlayerResult>): List<PlayerValue> {
        return results.flatMap { p1 ->
            results
                .drop(results.indexOf(p1) + 1)
                .flatMap { p2 ->
                    if (p1.score > p2.score)
                        listOf(PlayerValue(p1.player, 1), PlayerValue(p2.player, 0))
                    else
                        listOf(PlayerValue(p2.player, 1), PlayerValue(p1.player, 0))
                }
        }
    }
}

data class PlayerValue(
    val player: Player,
    val value: Int
)
