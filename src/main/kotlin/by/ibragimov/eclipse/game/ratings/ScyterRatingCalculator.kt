package by.ibragimov.eclipse.game.ratings

import by.ibragimov.eclipse.game.*
import com.ma.scyterrating.ScyterRatingHelper

/**
 * @author Andrew Kolubov
 */
class ScyterRatingCalculator : RatingCalculator {
    override fun calculate(season: SeasonGames): SeasonRatings {
        val helper = ScyterRatingHelper()
        helper.calculateSeason(season)
        val ranks = ArrayList<PlayerRating>()
        for (player: Player in players) {
            ranks.add(PlayerRating(player, player.rating))
        }
        return SeasonRatings(ranks)
    }
}
