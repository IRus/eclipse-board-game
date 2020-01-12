package by.ibragimov.eclipse.game.render

import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.TextStyle
import java.time.temporal.ChronoField
import java.util.Locale

val dayFormatter: DateTimeFormatter = DateTimeFormatterBuilder()
    .appendText(ChronoField.MONTH_OF_YEAR, TextStyle.SHORT)
    .appendLiteral(' ')
    .appendText(ChronoField.DAY_OF_MONTH)
    .toFormatter()
    .withLocale(Locale.US)
