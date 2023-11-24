package com.example.contestapp.data.source.local

import com.example.contestapp.data.local_model.DataLecture

interface LectureLocalDataSource {

    suspend fun insertLecture(dataLecture: DataLecture)
    suspend fun getLectures():List<DataLecture>
    suspend fun deleteLecture(dataLecture: DataLecture)
    suspend fun getLectureById(id:String):DataLecture

}