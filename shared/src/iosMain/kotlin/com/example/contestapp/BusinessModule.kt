package com.example.contestapp

import com.example.contestapp.database.ContestDatabase
import com.example.contestapp.di.initKoin
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual class BusinessModule {

    private val driver by lazy {  NativeSqliteDriver(ContestDatabase.Schema, "card.db") }
    actual fun init(){
        initKoin(
            database = ContestDatabase(driver),
            httpClient = getHttpClient(){
                install(ContentNegotiation){
                    json(Json{
                        prettyPrint = true
                        isLenient = true
                    })
                }
            }
        )
    }
    private fun getHttpClient(config: HttpClientConfig<*>.()-> Unit)= HttpClient(Darwin){
        config(this)
        engine{
            configureRequest{
                setAllowsCellularAccess(true)
            }
        }
    }

}