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
        return markdownTableGenerator.generate(Table(
            rows = listOf(
                Header(columns = listOf(
                    "Hello",
                    "World"
                )),
                Row(columns = listOf(
                    "Foo",
                    "Bar"
                ))
            )
        ))
    }
}

