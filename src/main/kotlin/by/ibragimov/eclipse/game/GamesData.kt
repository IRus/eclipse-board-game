package by.ibragimov.eclipse.game

import java.time.LocalDate
import java.time.Month.APRIL
import java.time.Month.DECEMBER
import java.time.Month.JANUARY
import java.time.Month.JULY
import java.time.Month.JUNE
import java.time.Month.NOVEMBER
import java.time.Month.OCTOBER
import java.time.Month.SEPTEMBER

private val Ruslan = Player("Ruslan")
private val Andrey = Player("Andrey")
private val Hleb = Player("Hleb")
private val Artur = Player("Artur")
private val Ilya = Player("Ilya")
private val Bronislav = Player("Bronislav")
private val Anna = Player("Anna")
private val Vlad = Player("Vlad")
private val Dima = Player("Dima")

val games = listOf(
    Game(
        date = LocalDate.of(2019, DECEMBER, 28),
        playerResults = listOf(
            PlayerResult(Ruslan, 16),
            PlayerResult(Dima, 39),
            PlayerResult(Hleb, 51),
            PlayerResult(Ilya, 23)
        )
    ),
    Game(
        date = LocalDate.of(2019, NOVEMBER, 10),
        playerResults = listOf(
            PlayerResult(Ruslan, 30),
            PlayerResult(Artur, 28),
            PlayerResult(Hleb, 35),
            PlayerResult(Ilya, 41)
        )
    ),
    Game(
        date = LocalDate.of(2018, OCTOBER, 11),
        playerResults = listOf(
            PlayerResult(Ruslan, -1),
            PlayerResult(Andrey, 50),
            PlayerResult(Hleb, -1),
            PlayerResult(Ilya, 38),
            PlayerResult(Bronislav, 17)
        )
    ),
    Game(
        date = LocalDate.of(2018, APRIL, 25),
        playerResults = listOf(
            PlayerResult(Ruslan, 36),
            PlayerResult(Andrey, 37),
            PlayerResult(Hleb, 31),
            PlayerResult(Ilya, 29)
        )
    ),
    Game(
        date = LocalDate.of(2018, JANUARY, 19),
        playerResults = listOf(
            PlayerResult(Ruslan, 54),
            PlayerResult(Artur, 53),
            PlayerResult(Hleb, 40)
        )
    ),
    Game(
        date = LocalDate.of(2018, JANUARY, 30),
        playerResults = listOf(
            PlayerResult(Ruslan, 29),
            PlayerResult(Artur, 42),
            PlayerResult(Ilya, 26),
            PlayerResult(Hleb, 46)
        )
    ),
    Game(
        date = LocalDate.of(2017, DECEMBER, 20),
        playerResults = listOf(
            PlayerResult(Ruslan, 42),
            PlayerResult(Ilya, 15),
            PlayerResult(Hleb, 24),
            PlayerResult(Andrey, 72)
        )
    ),
    Game(
        date = LocalDate.of(2017, NOVEMBER, 23),
        playerResults = listOf(
            PlayerResult(Ruslan, 17),
            PlayerResult(Artur, 32),
            PlayerResult(Hleb, 45),
            PlayerResult(Andrey, 51)
        )
    ),
    Game(
        date = LocalDate.of(2017, SEPTEMBER, 19),
        playerResults = listOf(
            PlayerResult(Ilya, 35),
            PlayerResult(Bronislav, 55),
            PlayerResult(Ruslan, -1),
            PlayerResult(Andrey, 59)
        )
    ),
    Game(
        date = LocalDate.of(2017, JULY, 4),
        playerResults = listOf(
            PlayerResult(Anna, 28),
            PlayerResult(Andrey, 50),
            PlayerResult(Ruslan, 46),
            PlayerResult(Ilya, 29)
        )
    ),
    Game(
        date = LocalDate.of(2017, JUNE, 23),
        playerResults = listOf(
            PlayerResult(Ruslan, 41),
            PlayerResult(Artur, 54),
            PlayerResult(Hleb, 68)
        )
    ),
    Game(
        date = LocalDate.of(2017, JUNE, 15),
        playerResults = listOf(
            PlayerResult(Ruslan, 29),
            PlayerResult(Vlad, 32),
            PlayerResult(Artur, 19),
            PlayerResult(Hleb, 40),
            PlayerResult(Andrey, 39)
        )
    )
)
