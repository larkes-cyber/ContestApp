package com.example.contestapp.data.source.remote

import com.example.contestapp.data.dto_model.DtoLecture
import com.example.contestapp.data.source.remote.LectureRemoteDataSource.Companion.GET_DOCX_URL
import com.example.contestapp.data.source.remote.LectureRemoteDataSource.Companion.GET_IMAGE_URL
import com.example.contestapp.data.source.remote.LectureRemoteDataSource.Companion.UPLOAD_IMAGE_URL
import com.example.contestapp.data.source.remote.LectureRemoteDataSource.Companion.UPLOAD_LECTURE_URL
import com.example.contestapp.resource.Constants.API_URL
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readBytes
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

class LectureRemoteDataSourceImpl(
    private val httpClient: HttpClient
):LectureRemoteDataSource {
    override suspend fun transformateAudio(
        title: String,
        subject: String,
        audio: ByteArray,
    ): DtoLecture {
        val response = httpClient.post(UPLOAD_LECTURE_URL) {
            url {
                parameters.append("title", title)
                parameters.append("subject", subject)
            }
            setBody(
                MultiPartFormDataContent(
                    formData {
                        append("file", audio, Headers.build {
                            append(HttpHeaders.ContentType, "audio/mpeg")
                            append(HttpHeaders.ContentDisposition, "filename=\"ktor_logo.png\"")
                        })
                    },
                    boundary = "WebAppBoundary"
                )
            )
        }

        println(response.bodyAsText())

        return Json.decodeFromString(response.bodyAsText())
    }

    override suspend fun uploadImage(id: String, file: ByteArray) {
        val response = httpClient.post(UPLOAD_IMAGE_URL) {
            url {
                parameters.append("id", id)
            }
            setBody(
                MultiPartFormDataContent(
                    formData {
                        append("file", file, Headers.build {
                            append(HttpHeaders.ContentType, "image/png")
                            append(HttpHeaders.ContentDisposition, "filename=\"ktor_logo.png\"")
                        })
                    },
                    boundary = "WebAppBoundary"
                )
            )
        }
    }

    override suspend fun getImage(id: String): ByteArray {
        val response = httpClient.get(GET_IMAGE_URL){
            url {
                parameters.append("id", id)
            }
        }
        return response.readBytes()
    }

    override suspend fun getDocxFile(id: String): ByteArray {
        val response = httpClient.post(GET_DOCX_URL){
            url {
                parameters.append("id", id)
            }
        }
        return response.readBytes()
    }
}