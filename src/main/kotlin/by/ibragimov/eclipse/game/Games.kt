package by.ibragimov.eclipse.game

import java.time.LocalDate

data class Season(
        val year: Int,
        val games: SeasonGames,
        val ratings: SeasonRatings
)

data class SeasonGames(
        val games: List<Game>
)

data class SeasonRatings(
        val ratings: List<PlayerRating>
)

data class Game(
        val date: LocalDate,
        val playerResults: List<PlayerResult>
)

/**
 * [score] Number or victory points.
 */
data class PlayerResult(
        val player: Player,
        val score: Double = DEFAULT_RESULT
) {
    constructor(player: Player, score: Int) : this(player, score.toDouble())

    companion object {
        const val DEFAULT_RESULT = 0.0
    }
}

data class PlayerRating(
        val player: Player,
        val rating: Double
) {
    constructor(player: Player, rating: Int) : this(player, rating.toDouble())
    constructor(player: Player) : this(player, player.rating)
}

data class Player(
        val name: String,
        var games: Int = DEFAULT_GAMES,
        var rating: Double = DEFAULT_RATING
) {
    var updatedRating: Double = 0.0

    companion object {
        const val DEFAULT_RATING = 700.0
        const val DEFAULT_GAMES = 0
    }

    fun updateGame() {
        rating = updatedRating;
        games++
    }
}
