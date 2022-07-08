package ru.castprograms.platformsuai.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import ru.castprograms.platformsuai.repository.news.NewsRepository
import ru.castprograms.platformsuai.repository.news.NewsService
import ru.castprograms.platformsuai.repository.timetable.TimetableRepository
import ru.castprograms.platformsuai.viewModels.MainViewModel
import ru.castprograms.platformsuai.repository.timetable.TimetableService

class CalendarApplication: Application() {
    private val module = module {
        single { TimetableService() }
        single { TimetableRepository(get()) }
        single { NewsService() }
        single { NewsRepository(get()) }
        single { MainViewModel(get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()
//        DynamicColors.applyToActivitiesIfAvailable(this)
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(applicationContext)
            modules(module)
        }
    }
}