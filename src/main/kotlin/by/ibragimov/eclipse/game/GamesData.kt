package by.ibragimov.eclipse.game

import java.time.LocalDate
import java.time.Month

val Ruslan = Player("Ruslan")
val Andrey = Player("Andrey")
val Hleb = Player("Hleb")
val Artur = Player("Artur")

val games = listOf(
    Game(
        date = LocalDate.of(2017, Month.NOVEMBER, 23),
        results = listOf(
            Result(Ruslan, Score(17)),
            Result(Artur, Score(32)),
            Result(Hleb, Score(45)),
            Result(Andrey, Score(51))
        )
    )
)