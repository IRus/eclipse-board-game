package by.ibragimov.eclipse.game

import java.time.LocalDate
import java.time.Month.DECEMBER
import java.time.Month.JULY
import java.time.Month.JUNE
import java.time.Month.NOVEMBER
import java.time.Month.SEPTEMBER

private val Ruslan = Player("Ruslan")
private val Andrey = Player("Andrey")
private val Hleb = Player("Hleb")
private val Artur = Player("Artur")
private val Ilya = Player("Ilya")
private val Bronislav = Player("Bronislav")
private val Anna = Player("Anna")
private val Vlad = Player("Vlad")

val games = listOf(
    Game(
        date = LocalDate.of(2017, DECEMBER, 20),
        results = listOf(
            Result(Ruslan, Score(42)),
            Result(Ilya, Score(15)),
            Result(Hleb, Score(24)),
            Result(Andrey, Score(72))
        )
    ),
    Game(
        date = LocalDate.of(2017, NOVEMBER, 23),
        results = listOf(
            Result(Ruslan, Score(17)),
            Result(Artur, Score(32)),
            Result(Hleb, Score(45)),
            Result(Andrey, Score(51))
        )
    ),
    Game(
        date = LocalDate.of(2017, SEPTEMBER, 19),
        results = listOf(
            Result(Ilya, Score(35)),
            Result(Bronislav, Score(55)),
            Result(Ruslan, Score(-1)),
            Result(Andrey, Score(59))
        )
    ),
    Game(
        date = LocalDate.of(2017, JULY, 4),
        results = listOf(
            Result(Anna, Score(28)),
            Result(Andrey, Score(50)),
            Result(Ruslan, Score(46)),
            Result(Ilya, Score(29))
        )
    ),
    Game(
        date = LocalDate.of(2017, JUNE, 23),
        results = listOf(
            Result(Ruslan, Score(41)),
            Result(Artur, Score(54)),
            Result(Hleb, Score(68))
        )
    ),
    Game(
        date = LocalDate.of(2017, JUNE, 15),
        results = listOf(
            Result(Ruslan, Score(29)),
            Result(Vlad, Score(32)),
            Result(Artur, Score(19)),
            Result(Hleb, Score(40)),
            Result(Andrey, Score(39))
        )
    )
)
