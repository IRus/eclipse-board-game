package com.scyterrating.models

import by.ibragimov.eclipse.game.Player
import ma.com.BaseUnitTest
import org.junit.Assert
import org.junit.Test

class UserUnitTest() : BaseUnitTest() {

    @Test
    @Throws(Exception::class)
    fun constructorUser_firstNoSecondNoThirdParam_paramDefaultDefault() {
        val user: Player = Player(ANDREW)
        Assert.assertEquals(ANDREW, user.name)
        Assert.assertEquals(Player.DEFAULT_GAMES, user.games)
        Assert.assertEquals(Player.DEFAULT_RATING, user.rating, RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun constructorUser_firstSecondNoThirdParam_paramParamDefault() {
        val gamePlayed: Int = 5;
        val user: Player = Player(ANDREW, gamePlayed)
        Assert.assertEquals(ANDREW, user.name)
        Assert.assertEquals(gamePlayed, user.games)
        Assert.assertEquals(Player.DEFAULT_RATING, user.rating, RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun constructorUser_firstSecondThirdParam_paramParamParam() {
        val gamePlayed: Int = 3;
        val currentRatting: Double = 123.456;
        val user: Player = Player(ANDREW, gamePlayed, currentRatting)
        Assert.assertEquals(ANDREW, user.name)
        Assert.assertEquals(gamePlayed, user.games)
        Assert.assertEquals(currentRatting, user.rating, RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun userSetter_games_set() {
        val user: Player = Player(ANDREW)
        user.games = 125
        Assert.assertEquals(125, user.games)
    }

    @Test
    @Throws(Exception::class)
    fun userSetter_rating_set() {
        val user: Player = Player(ANDREW)
        user.rating = 123.456
        Assert.assertEquals(123.456, user.rating, RATING_DELTA)
    }
}