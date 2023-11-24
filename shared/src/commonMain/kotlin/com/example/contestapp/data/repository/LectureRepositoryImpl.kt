package com.example.contestapp.data.repository

import com.example.contestapp.data.source.local.LectureLocalDataSource
import com.example.contestapp.data.source.remote.LectureRemoteDataSource
import com.example.contestapp.domain.mapper.toDataLecture
import com.example.contestapp.domain.mapper.toLecture
import com.example.contestapp.domain.model.Lecture
import com.example.contestapp.domain.repository.LectureRepository

class LectureRepositoryImpl(
    private val lectureLocalDataSource: LectureLocalDataSource,
    private val lectureRemoteDataSource: LectureRemoteDataSource
):LectureRepository {
    override suspend fun getLectures() = lectureLocalDataSource.getLectures().map { it.toLecture() }

    override suspend fun filterLecturesByTime(): List<Lecture> = lectureLocalDataSource.getLectures().sortedBy { it.time }.map { it.toLecture() }
    override suspend fun filterLecturesByFavorite(): List<Lecture> {
        return getLectures().filter { it.isFavorite }
    }

    override suspend fun filterLecturesByHeader(header:String): List<Lecture> {
       return getLectures().sortedBy { it.title }
    }

    override suspend fun updateLecturePhoto(id: String, byteArray: ByteArray) {
        lectureRemoteDataSource.uploadImage(id, byteArray)
    }

    override suspend fun getLectureImage(id: String): ByteArray = lectureRemoteDataSource.getImage(id)

    override suspend fun deleteLecture(lecture: Lecture) {
        lectureLocalDataSource.deleteLecture(lecture.toDataLecture())
    }

    override suspend fun addNewLecture(subject: String, title: String, byteArray: ByteArray) {
        lectureRemoteDataSource.transformateAudio(
            title = title,
            subject = subject,
            audio = byteArray
        )
    }

    override suspend fun editLecture(lecture: Lecture) {
        lectureLocalDataSource.insertLecture(lecture.toDataLecture())
    }

    override suspend fun makeFavorite(id: String) {
        val lecture = lectureLocalDataSource.getLectureById(id)!!
        lecture.isFavorite = true
        editLecture(lecture.toLecture())
    }

    override suspend fun searchForLecture(symbols: String):List<Lecture> {
        return getLectures().filter {
                        it.author.contains(symbols)
                        || it.descr.contains(symbols)
                        || it.shortDescr.contains(symbols)
                        || it.title.contains(symbols)
                        || it.subject.contains(symbols)
                        || it.title.contains(symbols)
        }
    }
}