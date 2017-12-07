package by.ibragimov.eclipse.game

import java.time.LocalDate
import java.time.Month

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
        date = LocalDate.of(2017, Month.NOVEMBER, 23),
        results = listOf(
            Result(Ruslan, Score(17)),
            Result(Artur, Score(32)),
            Result(Hleb, Score(45)),
            Result(Andrey, Score(51))
        )
    ),
    Game(
        date = LocalDate.of(2017, Month.SEPTEMBER, 19),
        results = listOf(
            Result(Ilya, Score(35)),
            Result(Bronislav, Score(55)),
            Result(Ruslan, Score(-1)),
            Result(Andrey, Score(59))
        )
    ),
    Game(
        date = LocalDate.of(2017, Month.JULY, 4),
        results = listOf(
            Result(Anna, Score(28)),
            Result(Andrey, Score(50)),
            Result(Ruslan, Score(46)),
            Result(Ilya, Score(29))
        )
    ),
    Game(
        date = LocalDate.of(2017, Month.JUNE, 23),
        results = listOf(
            Result(Ruslan, Score(41)),
            Result(Artur, Score(54)),
            Result(Hleb, Score(68))
        )
    ),
    Game(
        date = LocalDate.of(2017, Month.JUNE, 15),
        results = listOf(
            Result(Ruslan, Score(29)),
            Result(Vlad, Score(32)),
            Result(Artur, Score(19)),
            Result(Hleb, Score(40)),
            Result(Andrey, Score(39))
        )
    )
)