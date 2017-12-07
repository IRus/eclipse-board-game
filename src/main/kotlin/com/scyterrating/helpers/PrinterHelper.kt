package com.scyterrating.helpers

import com.scyterrating.models.GameResult
import com.scyterrating.models.User


class PrinterHelper {
    companion object {
        fun printGameResults(game: MutableList<GameResult>) {
            print( "Results: ")
            for (gameResult in game) {
                print(gameResult.user.name + " " + gameResult.result + "; ")
            }
            println()
        }

        fun printRating(players: MutableList<User>) {
            println("Players rating: ")
            val sortedPlayers = players.sortedByDescending { it.rating }
            for (player in sortedPlayers) {
                println(player.name + " " + "%.3f".format(player.rating) + "; ")
            }
            println()
        }
    }
}