package by.ibragimov.eclipse.game

import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.SignStyle
import java.time.format.TextStyle
import java.time.temporal.ChronoField
import java.util.*

val dayFormatter: DateTimeFormatter = DateTimeFormatterBuilder()
    .appendText(ChronoField.MONTH_OF_YEAR, TextStyle.SHORT)
    .appendLiteral(' ')
    .appendText(ChronoField.DAY_OF_MONTH)
    .appendLiteral(", ")
    .appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
    .toFormatter()
    .withLocale(Locale.US)
