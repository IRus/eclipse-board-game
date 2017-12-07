package com.scyterrating.helpers

import com.scyterrating.models.GameResult
import com.scyterrating.models.User

class RatingCalculator {
    companion object {

        const val BASE_RATING = 1000.0
        const val GAME_RATE = 30.0

        var gameNumber = 1

        fun isValid(game: List<GameResult>): Boolean {
            for (i in 0..game.size - 1) {
                val gameResult = game.get(i)
                for (j in i + 1..game.size - 1) {
                    val nextGameResult = game.get(j)
                    if (gameResult.user.name == nextGameResult.user.name) {
                        return false;
                    }
                }
            }
            return true
        }

        /**
         * Return Average points, earned by player (ПО в формуле)
         */
        fun getAverage(game: MutableList<GameResult>): Double {
            var sum = 0.0;
            for (i in 0..game.size - 1) {
                sum += game.get(i).result
            }
            return sum / game.size
        }

        /**
         * Return player's rate for game, which depends on player's played games (Ки в формуле)
         */
        fun getPlayerRate(games: Int): Long {
            when (games) {
                0 -> return 10
                in 1..2 -> return 5
                3 -> return 4
                4 -> return 3
                in 5..9 -> return 2
                else -> return 1
            }
        }

        /**
         * Return other players average rating (Рср в формуле)
         */
        fun otherPlayerAverageRating(user: User, game: MutableList<GameResult>): Double {
            var sum = 0.0
            var exists = false
            for (gameResult in game) {
                if (user == gameResult.user) {
                    exists = true;
                } else {
                    sum += gameResult.user.rating
                }
            }
            return if (exists) sum / (game.size - 1) else 0.0
        }

        /**
         * Return expected player's points rate in specific game (EarnedPoints / AveragePoints)
         * (вычетаемая дробь в скобках в формуле)
         */
        fun expectedPointsRate(user: User, game: MutableList<GameResult>): Double {
            return 2 / expectedRateDenominator(user, game)
        }

        /**
         * Return expected player's points rate in specific game (EarnedPoints / AveragePoints)
         * (вычетаемая дробь в скобках в формуле)
         */
        fun expectedPlaceRate(user: User, game: MutableList<GameResult>): Double {
            return 1 / expectedRateDenominator(user, game)
        }

        fun expectedRateDenominator(user: User, game: MutableList<GameResult>) : Double {
            val otherPlayerAverageRating = otherPlayerAverageRating(user, game)
            val pow = (otherPlayerAverageRating - user.rating) / BASE_RATING
            return 1 + Math.pow(10.0, pow)
        }

        /**
         * Return game's result's rate, depends on players and place (
         */
        fun getGameResultsRate(players: Int, place: Int): Double {
            when (players) {
                in 6..12 -> {
                    when (place) {
                        1 -> return 1.5
                        2 -> return 1.3
                        3 -> return 1.1
                        else -> return 1.0
                    }
                }
                5 -> {
                    when (place) {
                        1 -> return 1.4
                        2 -> return 1.2
                        3 -> return 1.1
                        else -> return 1.0
                    }
                }
                4 -> {
                    when (place) {
                        1 -> return 1.3
                        2 -> return 1.1
                        else -> return 1.0
                    }
                }
                3 -> {
                    when (place) {
                        1 -> return 1.1
                        else -> return 1.0
                    }
                }
                2 -> return 0.5
                else -> return 1.0
            }
        }


        fun getGameResult(players: Int, place: Int): Double {
            if (place == 1) {
                return 1.0
            }
            if (place == players) {
                return 0.0
            }
            return (players - place).toDouble() / (players - 1).toDouble()
        }

        /**
         * Return user place in specific game, need for game's result's rate
         */
        fun getUserPlace(userResult: GameResult, game: MutableList<GameResult>): Int {
            var place: Int
            place = game.size
            for (gameResult in game) {
                if (userResult.user != gameResult.user && userResult.result >= gameResult.result) {
                    place--
                }
            }
            return place
        }

        /**
         * Main method, which updates game result
         */
        fun updateRating(game: MutableList<GameResult>) {
            println("Game number: " + gameNumber)
            PrinterHelper.printGameResults(game)
            print("Rating change: ")
            val averageGameRating = getAverage(game)
            for (gameResult in game) {
                val userResult = (gameResult.result / averageGameRating - expectedPointsRate(gameResult.user, game)) * 0.2
                val playerRate = getPlayerRate(gameResult.user.games)
                //val gameResultsRate = getGameResultsRate(game.size, getUserPlace(gameResult, game))
              //  val placeResult = playerRate * ()
                val ratedUserResult = userResult * playerRate * GAME_RATE
                print(gameResult.user.name + " " + "%.3f".format(ratedUserResult) + "; ")
                gameResult.user.updatedRating = gameResult.user.rating + ratedUserResult
            }
            println()
            for (gameResult in game) {
                gameResult.user.updateGame()
            }
            gameNumber++
        }
    }
}