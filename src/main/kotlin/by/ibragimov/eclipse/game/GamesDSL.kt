package by.ibragimov.eclipse.game

import java.time.LocalDate
import java.time.Month

@DslMarker
annotation class GamesDSL

@GamesDSL
operator fun Player.rangeTo(score: Int): PlayerResult {
    return PlayerResult(
        player = this,
        score = score.toDouble()
    )
}

@GamesDSL
fun games(builder: GamesBuilder.() -> Unit): List<Game> {
    val holder = GamesBuilder()
    builder(holder)
    return holder.toGames()
}

class GamesBuilder {
    private val games = mutableListOf<Game>()

    /**
     * Fancy builder to create instances of [Game].
     *
     * @author Ruslan Ibragimov
     */
    @GamesDSL
    fun game(builder: GameBuilder.() -> Unit) {
        val holder = GameBuilder()
        builder(holder)
        games.add(holder.toGame())
    }

    fun toGames(): List<Game> {
        return games
    }
}


class GameBuilder {
    private var date: LocalDate = LocalDate.now()
    @GamesDSL
    fun JANUARY(day: Int, year: Int) {
        date = LocalDate.of(year, Month.of(1), day)
    }
    @GamesDSL
    fun FEBRUARY(day: Int, year: Int) {
        date = LocalDate.of(year, Month.of(2), day)
    }
    @GamesDSL
    fun MARCH(day: Int, year: Int) {
        date = LocalDate.of(year, Month.of(3), day)
    }
    @GamesDSL
    fun APRIL(day: Int, year: Int) {
        date = LocalDate.of(year, Month.of(4), day)
    }
    @GamesDSL
    fun MAY(day: Int, year: Int) {
        date = LocalDate.of(year, Month.of(5), day)
    }
    @GamesDSL
    fun JUNE(day: Int, year: Int) {
        date = LocalDate.of(year, Month.of(6), day)
    }
    @GamesDSL
    fun JULY(day: Int, year: Int) {
        date = LocalDate.of(year, Month.of(7), day)
    }
    @GamesDSL
    fun AUGUST(day: Int, year: Int) {
        date = LocalDate.of(year, Month.of(8), day)
    }
    @GamesDSL
    fun SEPTEMBER(day: Int, year: Int) {
        date = LocalDate.of(year, Month.of(9), day)
    }
    @GamesDSL
    fun OCTOBER(day: Int, year: Int) {
        date = LocalDate.of(year, Month.of(10), day)
    }
    @GamesDSL
    fun NOVEMBER(day: Int, year: Int) {
        date = LocalDate.of(year, Month.of(11), day)
    }
    @GamesDSL
    fun DECEMBER(day: Int, year: Int) {
        date = LocalDate.of(year, Month.of(12), day)
    }

    private var results = mutableListOf<PlayerResult>()
    @GamesDSL
    operator fun PlayerResult.unaryPlus() {
        results.add(this)
    }
    @GamesDSL
    fun alliance(builder: AllianceBuilder.() -> Unit) {
        val holder = AllianceBuilder()
        builder(holder)
        results.addAll(holder.toResult())
    }

    internal fun toGame(): Game {
        return Game(
            date = date,
            results = results
        )
    }
}


class AllianceBuilder {
    private val players = mutableListOf<PlayerResult>()
    @GamesDSL
    operator fun PlayerResult.unaryPlus() {
        players.add(this)
    }

    internal fun toResult(): List<PlayerResult> {
        val count = players.size
        val score = players.sumByDouble { it.score } / count

        return players.map { result -> result.copy(score = score) }
    }
}
