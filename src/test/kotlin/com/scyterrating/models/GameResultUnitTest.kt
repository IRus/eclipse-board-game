package com.scyterrating.models

import com.scyterrating.helpers.GameResult
import com.scyterrating.helpers.User
import ma.com.BaseUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GameResultUnitTest : BaseUnitTest() {

    @Test
    @Throws(Exception::class)
    fun constructorGameResult_firstNoSecondParam_paramDefault() {
        val user = User(ANDREW)
        val gameResult = GameResult(user)
        assertEquals(gameResult.user, user)
        assertEquals(gameResult.result, GameResult.DEFAULT_RESULT, RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun constructorGameResult_firstSecondParam_paramParam() {
        val user = User(ANDREW)
        val gameResult = GameResult(user, 25.0)
        assertEquals(gameResult.user, user)
        assertEquals(gameResult.result, 25.0, RATING_DELTA)
    }
}