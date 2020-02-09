package by.ibragimov.eclipse.game

import by.ibragimov.eclipse.game.model.Player
import by.ibragimov.eclipse.game.model.games

private val Ruslan = Player("Ruslan")
private val Andrey = Player("Andrey")
private val Hleb = Player("Hleb")
private val Artur = Player("Artur")
private val Ilya = Player("Ilya")
private val Bronislav = Player("Bronislav")
private val Anna = Player("Anna")
private val Vlad = Player("Vlad")
private val Dima = Player("Dima")
private val Artem = Player("Artem")

val games = games {
    game {
        FEBRUARY(8, 2020)
        Ilya..26
        Dima..24
        Ruslan..54
        Vlad..42
    }
    game {
        JANUARY(11, 2020)
        alliance {
            Ilya..30
            Ruslan..39
        }
        alliance {
            Dima..52
            Hleb..31
        }
        Artem..17
        Bronislav..4
        Andrey..45
    }
    game {
        DECEMBER(28, 2019)
        Ruslan..16
        Dima..39
        Hleb..51
        Ilya..23
    }
    game {
        NOVEMBER(10, 2019)
        Ruslan..30
        Artur..28
        Hleb..35
        Ilya..41
    }
    game {
        OCTOBER(11, 2018)
        Ruslan..-1
        Andrey..50
        Hleb..-1
        Ilya..38
        Bronislav..17
    }
    game {
        APRIL(25, 2018)
        Ruslan..36
        Andrey..37
        Hleb..31
        Ilya..29
    }
    game {
        JANUARY(19, 2018)
        Ruslan..54
        Artur..53
        Hleb..40
    }
    game {
        JANUARY(30, 2018)
        Ruslan..29
        Artur..42
        Ilya..26
        Hleb..46
    }
    game {
        DECEMBER(20, 2017)
        Ruslan..42
        Ilya..15
        Hleb..24
        Andrey..72
    }
    game {
        NOVEMBER(23, 2017)
        Ruslan..17
        Artur..32
        Hleb..45
        Andrey..51
    }
    game {
        SEPTEMBER(19, 2017)
        Ilya..35
        Bronislav..55
        Ruslan..-1
        Andrey..59
    }
    game {
        JULY(4, 2017)
        Anna..28
        Andrey..50
        Ruslan..46
        Ilya..29
    }
    game {
        JUNE(23, 2017)
        Ruslan..41
        Artur..54
        Hleb..68
    }
    game {
        JUNE(15, 2017)
        Ruslan..29
        Vlad..32
        Artur..19
        Hleb..40
        Andrey..39
    }
}
