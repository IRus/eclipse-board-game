package by.ibragimov.eclipse.game

import org.slf4j.LoggerFactory

object App {
    private val LOGGER = LoggerFactory.getLogger(App::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        LOGGER.info("Generation started")
        try {
            Calculator(
                DefaultFileWriter(),
                DefaultRatingCalculator(),
                DefaultGamesRenderer(DefaultMarkdownTableGenerator())
            ).run(games)
        } catch (e: Exception) {
            LOGGER.error("Error while processing.", e)
            System.exit(1)
        }
    }
}

class Calculator(
    private val fileWriter: FileWriter,
    private val ratingCalculator: RatingCalculator,
    private val gamesRenderer: GamesRenderer
) {
    fun run(games: List<Game>) {
        games.map(ratingCalculator::calculate).also { ratings ->
            gamesRenderer.render(ratings).also { index ->
                fileWriter.write("public", "index.md", index)
            }
        }
    }
}