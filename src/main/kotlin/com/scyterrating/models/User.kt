package com.scyterrating.models

data class User(var name: String, var games: Int = DEFAULT_GAMES, var rating: Double = DEFAULT_RATING, var updatedRating: Double = DEFAULT_RATING) {
    companion object {
        const val DEFAULT_RATING = 0.0
        const val DEFAULT_GAMES = 0
    }

    fun updateGame() {
        rating = updatedRating;
        games++
    }
}