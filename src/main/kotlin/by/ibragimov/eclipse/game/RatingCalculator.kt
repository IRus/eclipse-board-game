package by.ibragimov.eclipse.game

interface RatingCalculator {
    fun calculate(game: Game): Game
}

class DefaultRatingCalculator : RatingCalculator {
    override fun calculate(game: Game): Game {
        return game
    }
}