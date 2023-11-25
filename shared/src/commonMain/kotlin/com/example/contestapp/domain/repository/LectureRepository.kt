package com.example.contestapp.domain.repository

import com.example.contestapp.domain.model.Lecture
import com.example.contestapp.resource.Resource

interface LectureRepository {
    suspend fun getLectures():List<Lecture>
    suspend fun filterLecturesByTime():List<Lecture>
    suspend fun filterLecturesByFavorite():List<Lecture>
    suspend fun filterLecturesByHeader(header:String):List<Lecture>
    suspend fun updateLecturePhoto(id:String, byteArray: ByteArray): Resource<String>
    suspend fun getLectureImage(id:String):Resource<ByteArray>
    suspend fun deleteLecture(lecture: Lecture)
    suspend fun addNewLecture(author:String, subject:String, title:String, byteArray: ByteArray):Resource<String>
    suspend fun editLecture(lecture: Lecture)
    suspend fun makeFavorite(id:String)
    suspend fun searchForLecture(symbols:String):List<Lecture>

}