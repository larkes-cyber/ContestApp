package com.example.contestapp.data.source.local

import com.example.contestapp.data.local_model.DataDescr
import com.example.contestapp.data.local_model.DataLecture
import com.example.contestapp.database.ContestDatabase
import com.example.contestapp.domain.mapper.toDataLecture
import kotlinx.serialization.json.Json

class LectureLocalDataSourceImpl(
    private val db:ContestDatabase
):LectureLocalDataSource {

    private val queries = db.lectureQueries
    override suspend fun insertLecture(dataLecture: DataLecture) {
        deleteLecture(dataLecture)
        val descr = Json.encodeToString(DataDescr.serializer(), DataDescr(dataLecture.descr))
        queries.insertLecture(
            id = dataLecture.id,
            title = dataLecture.title,
            time = dataLecture.time,
            descr = descr,
            shortDescr = dataLecture.shortDescr,
            author = dataLecture.author,
            subject = dataLecture.subject,
            isFavorite = dataLecture.isFavorite
        )
    }

    override suspend fun getLectures() = queries.getLectures().executeAsList().map { it.toDataLecture() }
    override suspend fun deleteLecture(dataLecture: DataLecture) {
        queries.deleteLecture(dataLecture.id)
    }

    override suspend fun getLectureById(id: String): DataLecture = queries.getLectureById(id).executeAsOne().toDataLecture()
}