package ru.castprograms.platformsuai.repository.timetable

class TimetableRepository(private val timetableService: TimetableService) {
    private val timeLesson = arrayOf(
        "9:30-11:00",
        "11:10-12:40",
        "13:00-14:30",
        "15:00-16:30",
        "16:40-18:10",
        "18:30-20:00"
    )

    fun getTime(lessonNumber: Int) =
        if (lessonNumber - 1 in timeLesson.indices) timeLesson[lessonNumber - 1] else ""

    suspend fun getSemInfo() = timetableService.getSemInfo()
    suspend fun getGroups() = timetableService.getGroups()
    suspend fun getTimeTableGroup(groupNumber: String) =
        timetableService.getTimeTableGroup(groupNumber)
}