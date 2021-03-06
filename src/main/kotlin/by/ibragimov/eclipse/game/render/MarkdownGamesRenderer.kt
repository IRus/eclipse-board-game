package by.ibragimov.eclipse.game.render

import by.ibragimov.eclipse.game.model.Game
import by.ibragimov.eclipse.game.model.Player
import by.ibragimov.eclipse.game.model.PlayerResult
import by.ibragimov.eclipse.game.model.Season
import by.ibragimov.eclipse.game.model.SeasonGames
import by.ibragimov.eclipse.game.model.SeasonRatings

class MarkdownGamesRenderer(
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

        return """
            *# Results
            *
            *$body
            *
            * $gh
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
            find { it.player == player } ?: PlayerResult(player, 0.0)
        }
    }
}
