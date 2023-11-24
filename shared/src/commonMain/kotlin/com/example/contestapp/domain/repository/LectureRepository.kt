package com.example.contestapp.domain.repository

import com.example.contestapp.domain.model.Lecture

interface LectureRepository {
    suspend fun getLectures():List<Lecture>
    suspend fun filterLecturesByTime():List<Lecture>
    suspend fun filterLecturesByFavorite():List<Lecture>
    suspend fun filterLecturesByHeader(header:String):List<Lecture>
    suspend fun updateLecturePhoto(id:String, byteArray: ByteArray)
    suspend fun getLectureImage(id:String):ByteArray
    suspend fun deleteLecture(lecture: Lecture)
    suspend fun addNewLecture(subject:String, title:String, byteArray: ByteArray)
    suspend fun editLecture(lecture: Lecture)
    suspend fun makeFavorite(id:String)
    suspend fun searchForLecture(symbols:String):List<Lecture>

}