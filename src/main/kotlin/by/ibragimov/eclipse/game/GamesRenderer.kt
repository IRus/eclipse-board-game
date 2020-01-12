package by.ibragimov.eclipse.game

interface GamesRenderer {
    fun render(seasons: List<Season>): String
}

class DefaultGamesRenderer(
    private val markdownTableGenerator: MarkdownTableGenerator
) : GamesRenderer {
    override fun render(seasons: List<Season>): String {
        return template(
            seasons.joinToString(separator = "\n") {
                season(it.year, games(it.games, it.ratings))
            }
        )
    }

    private fun template(body: String): String {
        val gh = "[GitHub](https://github.com/IRus/eclipse-board-game)"
        val ical = "[Calendar](https://irus.github.io/eclipse-board-game/calendar.ics)"

        return """
            *# Results
            *
            *$body
            *
            * $gh | $ical
        """.trimMargin("*")
    }

    private fun season(year: Int, games: String): String {
        return """
            *## Season $year
            *
            *$games
        """.trimMargin("*")
    }

    private fun games(seasonGames: SeasonGames, seasonRatings: SeasonRatings): String {
        val (games) = seasonGames
        val header = Header(listOf("Players") + games.sortedBy { it.date }.map { it.date.format(dayFormatter) } + listOf("Total"))

        val seasonPlayers = games
            .flatMap { it.results.map { result -> result.player } }
            .distinct()

        val rows = listOf(header) + games
            .map { it.copy(results = it.results.fillPlayers(seasonPlayers)) }
            .flatMap { game -> game.results.map { result -> game to result } }
            .groupBy { it.second.player }
            .map { it.toRow(seasonRatings) }
            .sortedByDescending { it.columns.last().toInt() }

        return markdownTableGenerator.generate(Table(
            rows = rows
        ))
    }

    private fun Map.Entry<Player, List<Pair<Game, PlayerResult>>>.toRow(seasonRatings: SeasonRatings): Row {
        val pre = listOf(this.key.name)
        val after = seasonRatings.ratings.firstOrNull { it.player == this.key }
            ?: throw IllegalStateException("Rating doesn't contain season player '${this.key}'.")

        return Row(pre + this.value.sortedBy { it.first.date }.map { "${it.second.score}" } + after.rating.toString())
    }

    private fun List<PlayerResult>.fillPlayers(seasonPlayers: List<Player>): List<PlayerResult> {
        return seasonPlayers.map { player ->
            val result = this.find { it.player == player }
            if (result != null) result else PlayerResult(player, 0.0)
        }
    }
}

