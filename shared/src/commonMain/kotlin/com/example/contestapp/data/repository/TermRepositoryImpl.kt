package com.example.contestapp.data.repository

import com.example.contestapp.data.source.local.TermLocalDataSource
import com.example.contestapp.domain.mapper.toDataTerm
import com.example.contestapp.domain.mapper.toTerm
import com.example.contestapp.domain.model.Term
import com.example.contestapp.domain.repository.TermRepository

class TermRepositoryImpl(
    private val termLocalDataSource: TermLocalDataSource
):TermRepository {
    override suspend fun addNewTerm(term: Term) {
        termLocalDataSource.insertTerm(term.toDataTerm())
    }

    override suspend fun getTermsByLectureId(lectureId:String) = termLocalDataSource.getTermsByLectureId(lectureId).map { it.toTerm() }

}