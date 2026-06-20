package com.example.courses.di

import android.content.Context
import androidx.room.Room
import com.example.courses.data.local.CourseDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) : CourseDataBase =
        Room.databaseBuilder(
            context.applicationContext,
            CourseDataBase::class.java,
            "course.db"
        )
            .build()

    @Provides
    fun provideCourseDao(db: CourseDataBase) = db.courseDao()
}