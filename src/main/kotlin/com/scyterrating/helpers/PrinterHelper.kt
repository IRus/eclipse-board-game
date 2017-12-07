package com.scyterrating.helpers


class PrinterHelper {
    companion object {
        fun printGameResults(game: List<GameResult>) {
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