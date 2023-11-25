package com.example.contestapp.di

import com.example.contestapp.data.repository.LectureRepositoryImpl
import com.example.contestapp.data.repository.TermRepositoryImpl
import com.example.contestapp.data.source.local.LectureLocalDataSource
import com.example.contestapp.data.source.local.LectureLocalDataSourceImpl
import com.example.contestapp.data.source.local.TermLocalDataSource
import com.example.contestapp.data.source.local.TermLocalDataSourceImpl
import com.example.contestapp.data.source.remote.LectureRemoteDataSource
import com.example.contestapp.data.source.remote.LectureRemoteDataSourceImpl
import com.example.contestapp.database.ContestDatabase
import com.example.contestapp.domain.model.Lecture
import com.example.contestapp.domain.repository.LectureRepository
import com.example.contestapp.domain.repository.TermRepository
import io.ktor.client.HttpClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(
    database: ContestDatabase,
    httpClient: HttpClient
) = startKoin {

    modules(
        module {
            single { httpClient }
            single { database }
            single<LectureLocalDataSource> { LectureLocalDataSourceImpl(get()) }
            single<TermLocalDataSource> { TermLocalDataSourceImpl(get()) }
            single<LectureRemoteDataSource> { LectureRemoteDataSourceImpl(get()) }
            single<LectureRepository> { LectureRepositoryImpl(get(), get(), get()) }
            single<TermRepository> { TermRepositoryImpl(get()) }
        }
    )

}

object Repositories: KoinComponent {
    fun lectureRepository() = get<LectureRepository>()
    fun termRepository() = get<TermRepository>()
}