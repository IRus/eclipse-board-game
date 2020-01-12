package by.ibragimov.eclipse.game.render

import by.ibragimov.eclipse.game.model.Season

interface GamesRenderer {
    fun render(seasons: List<Season>): String
}

