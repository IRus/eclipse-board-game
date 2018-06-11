package com.scyterrating.models

import com.scyterrating.helpers.User
import ma.com.BaseUnitTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UserUnitTest : BaseUnitTest() {

    @Test
    fun constructorUser_firstNoSecondNoThirdParam_paramDefaultDefault() {
        val user: User = User(ANDREW)
        assertEquals(ANDREW, user.name)
        assertEquals(User.DEFAULT_GAMES, user.games)
        assertEquals(User.DEFAULT_RATING, user.rating, RATING_DELTA)
    }

    @Test
    fun constructorUser_firstSecondNoThirdParam_paramParamDefault() {
        val gamePlayed: Int = 5;
        val user: User = User(ANDREW, gamePlayed)
        assertEquals(ANDREW, user.name)
        assertEquals(gamePlayed, user.games)
        assertEquals(User.DEFAULT_RATING, user.rating, RATING_DELTA)
    }

    @Test
    fun constructorUser_firstSecondThirdParam_paramParamParam() {
        val gamePlayed: Int = 3;
        val currentRatting: Double = 123.456;
        val user: User = User(ANDREW, gamePlayed, currentRatting)
        assertEquals(ANDREW, user.name)
        assertEquals(gamePlayed, user.games)
        assertEquals(currentRatting, user.rating, RATING_DELTA)
    }

    @Test
    fun userSetter_name_set() {
        val user: User = User(ANDREW)
        user.name = GLEB
        assertEquals(GLEB, user.name)
    }

    @Test
    fun userSetter_games_set() {
        val user: User = User(ANDREW)
        user.games = 125
        assertEquals(125, user.games)
    }

    @Test
    fun userSetter_rating_set() {
        val user: User = User(ANDREW)
        user.rating = 123.456
        assertEquals(123.456, user.rating, RATING_DELTA)
    }
}
