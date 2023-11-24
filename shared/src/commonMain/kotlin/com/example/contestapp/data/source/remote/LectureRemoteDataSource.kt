package com.example.contestapp.data.source.remote

import com.example.contestapp.data.dto_model.DtoLecture
import com.example.contestapp.resource.Constants.API_URL

interface LectureRemoteDataSource {

    suspend fun transformateAudio(title:String, subject:String, audio:ByteArray):DtoLecture
    suspend fun uploadImage(id:String, file:ByteArray)
    suspend fun getImage(id:String):ByteArray
    suspend fun getDocxFile(id:String):ByteArray

    companion object{
        const val UPLOAD_LECTURE_URL = "$API_URL/upload_lecture"
        const val UPLOAD_IMAGE_URL = "$API_URL/upload_image"
        const val GET_IMAGE_URL = "$API_URL/get_image"
        const val GET_DOCX_URL = "$API_URL/get_docx"
    }

}