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
        return """
            *# Results
            *
            *$body
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
        val header = Header(listOf("Players") + games.map { it.date.format(dayFormatter) } + listOf("Total"))

        val rows = listOf(header) + games
            .flatMap { game -> game.results.map { result -> game to result } }
            .groupBy { it.second.player }
            .map { it.toRow() }

        return markdownTableGenerator.generate(Table(
            rows = rows
        ))
    }

    private fun Map.Entry<Player, List<Pair<Game, Result>>>.toRow(): Row {
        val pre = listOf(this.key.name)
        val after = this.value.sumBy { it.second.score.value }.toString() // TODO value->rating!!!

        return Row(pre + this.value.map { "${it.second.score.value}/${it.second.score.rating}" } + after)
    }
}
