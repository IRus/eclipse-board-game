package com.scyterrating.models

import by.ibragimov.eclipse.game.Player
import by.ibragimov.eclipse.game.PlayerResult
import ma.com.BaseUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GamePlayerResultUnitTest : BaseUnitTest() {

    @Test
    @Throws(Exception::class)
    fun constructorGameResult_firstNoSecondParam_paramDefault() {
        val user = Player(ANDREW)
        val gameResult = PlayerResult(user)
        assertEquals(gameResult.player, user)
        assertEquals(gameResult.score, PlayerResult.DEFAULT_RESULT, RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun constructorGameResult_firstSecondParam_paramParam() {
        val user = Player(ANDREW)
        val gameResult = PlayerResult(user, 25.0)
        assertEquals(gameResult.player, user)
        assertEquals(gameResult.score, 25.0, RATING_DELTA)
    }
}
