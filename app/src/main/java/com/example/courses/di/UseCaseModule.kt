package com.example.courses.di

import com.example.courses.data.repository.CourseRepositoryImpl
import com.example.courses.domain.usecase.AddToFavouriteUseCase
import com.example.courses.domain.usecase.DeleteFromFavouriteUseCase
import com.example.courses.domain.usecase.GetCoursesUseCase
import com.example.courses.domain.usecase.GetFavouriteCoursesUseCase
import com.example.courses.domain.usecase.RefreshCoursesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideAddToFavouriteUseCase(repository: CourseRepositoryImpl): AddToFavouriteUseCase =
        AddToFavouriteUseCase(repository)

    @Provides
    fun provideDeleteFromFavouriteUseCase(repository: CourseRepositoryImpl): DeleteFromFavouriteUseCase =
        DeleteFromFavouriteUseCase(repository)

    @Provides
    fun provideGetCoursesUseCase(repository: CourseRepositoryImpl): GetCoursesUseCase =
        GetCoursesUseCase(repository)

    @Provides
    fun provideGetFavouriteCoursesUseCase(repository: CourseRepositoryImpl): GetFavouriteCoursesUseCase =
        GetFavouriteCoursesUseCase(repository)

    @Provides
    fun provideRefreshCoursesUseCase(repository: CourseRepositoryImpl): RefreshCoursesUseCase =
        RefreshCoursesUseCase(repository)
}