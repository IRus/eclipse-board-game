package com.scyterrating.helpers

import by.ibragimov.eclipse.game.Player
import by.ibragimov.eclipse.game.PlayerResult


class PrinterHelper {
    companion object {
        fun printGameResults(game: List<PlayerResult>) {
            print("Results: ")
            for (gameResult in game) {
                print(gameResult.player.name + " " + gameResult.score + "; ")
            }
            println()
        }

        fun printRating(players: MutableList<Player>) {
            println("Players rating: ")
            val sortedPlayers = players.sortedByDescending { it.rating }
            for (player in sortedPlayers) {
                println(player.name + " " + "%.3f".format(player.rating) + "; ")
            }
            println()
        }
    }
}