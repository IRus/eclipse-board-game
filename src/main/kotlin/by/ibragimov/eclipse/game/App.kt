package by.ibragimov.eclipse.game

import by.ibragimov.eclipse.game.model.Game
import by.ibragimov.eclipse.game.model.Season
import by.ibragimov.eclipse.game.model.SeasonGames
import by.ibragimov.eclipse.game.ratings.RatingCalculator
import by.ibragimov.eclipse.game.ratings.SimpleRatingCalculator
import by.ibragimov.eclipse.game.render.DefaultFileWriter
import by.ibragimov.eclipse.game.render.DefaultMarkdownTableGenerator
import by.ibragimov.eclipse.game.render.FileWriter
import by.ibragimov.eclipse.game.render.GamesRenderer
import by.ibragimov.eclipse.game.render.MarkdownGamesRenderer
import org.slf4j.LoggerFactory
import kotlin.system.exitProcess

object App {
    private val LOGGER = LoggerFactory.getLogger(App::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        LOGGER.info("Generation started")
        try {
            Calculator(
                DefaultFileWriter(),
                SimpleRatingCalculator(),
                MarkdownGamesRenderer(DefaultMarkdownTableGenerator())
            ).run(games)
        } catch (e: Exception) {
            LOGGER.error("Error while processing.", e)
            exitProcess(1)
        }
        LOGGER.info("Generation done")
    }
}

class Calculator(
    private val fileWriter: FileWriter,
    private val ratingCalculator: RatingCalculator,
    private val gamesRenderer: GamesRenderer
) {
    fun run(games: List<Game>) {
        games
            .groupBy { it.date.year }
            .map { Season(it.key, SeasonGames(it.value), ratingCalculator.calculate(SeasonGames(it.value))) }
            .also { seasons ->
                gamesRenderer.render(seasons).also { index ->
                    fileWriter.write("public", "index.md", index)
                }
            }
    }
}
