package com.example.contestapp.domain.mapper

import com.example.contestapp.data.local_model.DataLecture
import com.example.contestapp.domain.model.Lecture
import database.LectureEntity

fun LectureEntity.toDataLecture():DataLecture{
    return DataLecture(
        id = id,
        title = title,
        time = time,
        descr = descr,
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
        time = time.toString(),
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