package by.ibragimov.eclipse.game

import java.time.LocalDate

data class Game(
    val date: LocalDate,
    val results: List<Result>
) {
    val playersNumber: Int
        get() = results.size
}

data class Result(
    val player: Player,
    val score: Score
)

data class Player(
    val name: String
)

data class Score(
    val value: Int,
    val rating: Int = 0
)