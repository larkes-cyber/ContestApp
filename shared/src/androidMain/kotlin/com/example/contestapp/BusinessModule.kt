package com.example.contestapp

import android.content.Context
import com.example.contestapp.database.ContestDatabase
import com.example.contestapp.di.initKoin
import com.squareup.sqldelight.android.AndroidSqliteDriver
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual class BusinessModule(private val context: Context) {
    actual fun init() {
        initKoin(
            database = ContestDatabase(AndroidSqliteDriver(ContestDatabase.Schema, context, "contest.db")),
            httpClient = getHttpClient()
        )
    }
    private fun getHttpClient() = HttpClient(CIO) {
        install(ContentNegotiation){
            json(Json{
                prettyPrint = true
                isLenient = true
            })
        }
    }
}