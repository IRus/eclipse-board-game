package com.scyterrating.models

data class GameResult (val user: User, var result: Double = DEFAULT_RESULT){
    companion object {
        const val DEFAULT_RESULT = 0.0
    }
}
