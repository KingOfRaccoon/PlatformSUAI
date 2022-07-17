package ru.castprograms.calendarkmmsuai.data.time

import kotlinx.datetime.LocalDateTime
import ru.castprograms.platformsuai.data.time.DataTime


class DataTimeWithDifferentWeek(localDateTime: LocalDateTime, val isRed: Boolean): DataTime(localDateTime) {

    fun getTypeWeek() = if (isRed) 1 else 2
}