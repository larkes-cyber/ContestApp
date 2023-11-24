package com.example.contestapp.data.source.local

import com.example.contestapp.data.local_model.DataTerm
import com.example.contestapp.database.ContestDatabase
import com.example.contestapp.domain.mapper.toDataTerm

class TermLocalDataSourceImpl(
    private val db:ContestDatabase
):TermLocalDataSource {

    private val queries = db.termQueries
    override suspend fun insertTerm(dataTerm: DataTerm) {
        if(dataTerm.id != null) deleteTerm(dataTerm)
        queries.insertTerm(
            id = null,
            title = dataTerm.title,
            explanation = dataTerm.explanation,
            lectureId = dataTerm.lectureId
        )
    }

    override suspend fun deleteTerm(term: DataTerm) {
        queries.deleteTerm(term.id!!)
    }

    override suspend fun getTermsByLectureId(lectureId: String) = queries.getTermsByLectureId(lectureId).executeAsList().map { it.toDataTerm() }
}