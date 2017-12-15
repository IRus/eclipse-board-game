package by.ibragimov.eclipse.game

interface GamesRenderer {
    fun render(games: List<Game>): String
}

class DefaultGamesRenderer(
    private val markdownTableGenerator: MarkdownTableGenerator
) : GamesRenderer {
    override fun render(games: List<Game>): String {
        return template(
            games
                .groupBy { it.date.year }
                .map { season(it.key, games(it.value)) }
                .joinToString(separator = "\n")
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

    private fun games(games: List<Game>): String {
        val header = Header(listOf("Players") + games.sortedBy { it.date }.map { it.date.format(dayFormatter) } + listOf("Total"))

        val seasonPlayers = games
            .flatMap { it.results }
            .groupBy { it.player }
            .map { it.key }

        val rows = listOf(header) + games
            .map { it.copy(results = it.results.fillPlayers(seasonPlayers)) }
            .flatMap { game -> game.results.map { result -> game to result } }
            .groupBy { it.second.player }
            .map { it.toRow() }
            .sortedByDescending { it.columns.last().toInt() }

        return markdownTableGenerator.generate(Table(
            rows = rows
        ))
    }

    private fun Map.Entry<Player, List<Pair<Game, Result>>>.toRow(): Row {
        val pre = listOf(this.key.name)
        val after = this.value.sumBy { it.second.score.rating }.toString()

        return Row(pre + this.value.sortedBy { it.first.date }.map { "${it.second.score.value}/${it.second.score.rating}" } + after)
    }

    private fun List<Result>.fillPlayers(seasonPlayers: List<Player>): List<Result> {
        return seasonPlayers.map { player ->
            val result = this.find { it.player == player }
            if (result != null) result else Result(player, Score(0))
        }
    }
}

