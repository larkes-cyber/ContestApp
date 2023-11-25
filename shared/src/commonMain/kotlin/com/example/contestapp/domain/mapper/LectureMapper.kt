package com.example.contestapp.domain.mapper

import com.example.contestapp.data.dto_model.DtoLecture
import com.example.contestapp.data.local_model.DataDescr
import com.example.contestapp.data.local_model.DataLecture
import com.example.contestapp.domain.model.Lecture
import database.LectureEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun LectureEntity.toDataLecture():DataLecture{

        val descriprion:DataDescr = Json.decodeFromString(descr)
        return DataLecture(
            id = id,
            title = title,
            time = time,
            descr = descriprion.descr,
            shortDescr = shortDescr,
            author = author,
            subject = subject,
            isFavorite = isFavorite!!
        )
}

fun DataLecture.toLecture():Lecture{
    return Lecture(
        id = id,
        title = title,
        time = "25.11.23",
        descr = descr,
        shortDescr = shortDescr,
        author = author,
        subject = subject,
        isFavorite = isFavorite!!
    )
}

fun Lecture.toDataLecture():DataLecture{
    return DataLecture(
        id = id,
        title = title,
        time = time.toLong(),
        descr = descr,
        shortDescr = shortDescr,
        author = author,
        subject = subject,
        isFavorite = isFavorite!!
    )
}

