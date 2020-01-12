package by.ibragimov.eclipse.game.ratings

import by.ibragimov.eclipse.game.model.SeasonGames
import by.ibragimov.eclipse.game.model.SeasonRatings

interface RatingCalculator {
    fun calculate(season: SeasonGames): SeasonRatings
}
