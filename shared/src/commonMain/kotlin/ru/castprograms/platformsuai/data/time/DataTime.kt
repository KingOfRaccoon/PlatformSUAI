package ru.castprograms.platformsuai.data.time

import kotlinx.datetime.*

open class DataTime(
    var year: Int = 0,
    var mouth: Int = 0,
    var dayOfMouth: Int = 0,
    var hour: Int = 0,
    var minute: Int = 0,
    var dayOfWeek: Int = 0,
) {
    constructor(localDateTime: LocalDateTime) : this(
        localDateTime.year,
        localDateTime.month.number,
        localDateTime.dayOfMonth,
        localDateTime.hour,
        localDateTime.minute,
        localDateTime.dayOfWeek.isoDayNumber,
    )

    constructor(string: String) : this() {
        string.split("T").let {
            it[0].split("-").let { date ->
                year = date[0].toInt()
                mouth = date[1].toInt()
                dayOfMouth = date[2].toInt()
            }

            it[1].split(":").let { time ->
                hour = time[0].toInt()
                minute = time[1].toInt()
            }
        }
    }

    fun getDate(): String {
        val localDateTime = LocalDateTime(year, mouth, dayOfMouth, hour, minute)
            .toInstant(TimeZone.currentSystemDefault())
            .toLocalDateTime(TimeZone.currentSystemDefault())

        var string = ""
        val date = now()
        when (date.dayOfMouth - localDateTime.dayOfMonth) {
            -1 -> string += "Завтра"
            0 -> string += "Сегодня"
            1 -> string += "Вчера"
            else -> {
                string += localDateTime.dayOfMonth.toString()
                string += " "
                string += getMouthForTime()
            }
        }
        return string
    }

    fun getDateAndTime() = getDate() + ", ${hour}:" + if (minute >= 10) "$minute" else "0$minute"

    fun getShortcutDayOfWeek() = when (dayOfWeek) {
        DayOfWeek.MONDAY.isoDayNumber -> "Пн"
        DayOfWeek.TUESDAY.isoDayNumber -> "Вт"
        DayOfWeek.WEDNESDAY.isoDayNumber -> "Ср"
        DayOfWeek.THURSDAY.isoDayNumber -> "Чт"
        DayOfWeek.FRIDAY.isoDayNumber -> "Пт"
        DayOfWeek.SATURDAY.isoDayNumber -> "Сб"
        DayOfWeek.SUNDAY.isoDayNumber -> "Вс"
        else -> ""
    }

    fun getMouthAndYear() = getMouth() + " $year"

    fun getDayOfWeekText() = when (dayOfWeek) {
        DayOfWeek.MONDAY.isoDayNumber -> "Понедельник"
        DayOfWeek.TUESDAY.isoDayNumber -> "Вторник"
        DayOfWeek.WEDNESDAY.isoDayNumber -> "Среда"
        DayOfWeek.THURSDAY.isoDayNumber -> "Четверг"
        DayOfWeek.FRIDAY.isoDayNumber -> "Пятница"
        DayOfWeek.SATURDAY.isoDayNumber -> "Суббота"
        DayOfWeek.SUNDAY.isoDayNumber -> "Воскресенье"
        else -> ""
    }

    override fun toString(): String {
        return "$dayOfMouth.${mouth + 1}.$year"
    }

    private fun getMouth() = when (mouth) {
        1 -> "Январь"
        2 -> "Февраль"
        3 -> "Март"
        4 -> "Апрель"
        5 -> "Май"
        6 -> "Июнь"
        7 -> "Июль"
        8 -> "Август"
        9 -> "Сентябр"
        10 -> "Октябрь"
        11 -> "Ноябрь"
        12 -> "Декабрь"
        else -> ""
    }

    private fun getMouthForTime() = when (mouth) {
        1 -> "Января"
        2 -> "Февраля"
        3 -> "Марта"
        4 -> "Апреля"
        5 -> "Мая"
        6 -> "Июня"
        7 -> "Июля"
        8 -> "Августа"
        9 -> "Сентября"
        10 -> "Октября"
        11 -> "Ноября"
        12 -> "Декабря"
        else -> ""
    }

    companion object {
        fun now() = DataTime(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()))
    }
}