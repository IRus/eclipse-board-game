package com.scyterrating.models

import com.scyterrating.helpers.User
import ma.com.BaseUnitTest
import org.junit.Assert
import org.junit.Test

class UserUnitTest(): BaseUnitTest() {

    @Test
    @Throws(Exception::class)
    fun constructorUser_firstNoSecondNoThirdParam_paramDefaultDefault() {
        val user: User = User(ANDREW)
        Assert.assertEquals(ANDREW, user.name)
        Assert.assertEquals(User.DEFAULT_GAMES, user.games)
        Assert.assertEquals(User.DEFAULT_RATING, user.rating, RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun constructorUser_firstSecondNoThirdParam_paramParamDefault() {
        val gamePlayed: Int = 5;
        val user: User = User(ANDREW, gamePlayed)
        Assert.assertEquals(ANDREW, user.name)
        Assert.assertEquals(gamePlayed, user.games)
        Assert.assertEquals(User.DEFAULT_RATING, user.rating, RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun constructorUser_firstSecondThirdParam_paramParamParam() {
        val gamePlayed: Int = 3;
        val currentRatting: Double = 123.456;
        val user: User = User(ANDREW, gamePlayed, currentRatting)
        Assert.assertEquals(ANDREW, user.name)
        Assert.assertEquals(gamePlayed, user.games)
        Assert.assertEquals(currentRatting, user.rating, RATING_DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun userSetter_name_set() {
        val user: User = User(ANDREW)
        user.name = GLEB
        Assert.assertEquals(GLEB, user.name)
    }

    @Test
    @Throws(Exception::class)
    fun userSetter_games_set() {
        val user: User = User(ANDREW)
        user.games = 125
        Assert.assertEquals(125, user.games)
    }

    @Test
    @Throws(Exception::class)
    fun userSetter_rating_set() {
        val user: User = User(ANDREW)
        user.rating = 123.456
        Assert.assertEquals(123.456, user.rating, RATING_DELTA)
    }
}