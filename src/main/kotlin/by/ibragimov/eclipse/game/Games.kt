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
    val score: Int
)

data class PlayerRating(
    val player: Player,
    val rating: Int
)

data class Player(
    val name: String
)
