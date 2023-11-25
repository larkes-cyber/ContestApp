package com.example.contestapp.data.repository

import com.example.contestapp.data.local_model.DataLecture
import com.example.contestapp.data.local_model.DataTerm
import com.example.contestapp.data.source.local.LectureLocalDataSource
import com.example.contestapp.data.source.local.TermLocalDataSource
import com.example.contestapp.data.source.remote.LectureRemoteDataSource
import com.example.contestapp.domain.mapper.toDataLecture
import com.example.contestapp.domain.mapper.toLecture
import com.example.contestapp.domain.model.Lecture
import com.example.contestapp.domain.repository.LectureRepository
import com.example.contestapp.resource.Resource

class LectureRepositoryImpl(
    private val lectureLocalDataSource: LectureLocalDataSource,
    private val lectureRemoteDataSource: LectureRemoteDataSource,
    private val termLocalDataSource: TermLocalDataSource
):LectureRepository {
    override suspend fun getLectures() = lectureLocalDataSource.getLectures().map { it.toLecture() }

    override suspend fun filterLecturesByTime(): List<Lecture> = lectureLocalDataSource.getLectures().sortedBy { it.time }.map { it.toLecture() }
    override suspend fun filterLecturesByFavorite(): List<Lecture> {
        return getLectures().filter { it.isFavorite }
    }

    override suspend fun filterLecturesByHeader(header:String): List<Lecture> {
       return getLectures().sortedBy { it.title }
    }

    override suspend fun updateLecturePhoto(id: String, byteArray: ByteArray):Resource<String> {
       return try {
           lectureRemoteDataSource.uploadImage(id, byteArray)
            Resource.Success("success")
        }catch (e:Exception){
           Resource.Success(e.message.toString())
       }
    }

    override suspend fun getLectureImage(id: String): Resource<ByteArray> {
        return try {
            Resource.Success(lectureRemoteDataSource.getImage(id))
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun deleteLecture(lecture: Lecture) {
        lectureLocalDataSource.deleteLecture(lecture.toDataLecture())
    }

    override suspend fun addNewLecture(author:String, subject: String, title: String, byteArray: ByteArray):Resource<String> {
        try {
            val resp = lectureRemoteDataSource.transformateAudio(
                title = title,
                subject = subject,
                audio = byteArray
            )
            lectureLocalDataSource.insertLecture(
                DataLecture(
                    id = resp.id,
                    subject = subject,
                    title = title,
                    time = 2332332,
                    shortDescr = resp.short_descr,
                    author = author,
                    isFavorite = false,
                    descr = resp.text
                )
            )
            resp.terms.forEach {term ->
                termLocalDataSource.insertTerm(DataTerm(
                    title = term[0],
                    explanation = term[1],
                    lectureId = resp.id
                ))
            }
            println(resp.id)
            return Resource.Success(resp.id)
        }catch (e:Exception){
            println(e.message.toString())
            return Resource.Error(e.message!!)
        }
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

                         it.title.contains(symbols, ignoreCase = true)

        }
    }
}