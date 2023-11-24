package com.example.contestapp.domain.repository

import com.example.contestapp.domain.model.Term

interface TermRepository {
    suspend fun addNewTerm(term: Term)
    suspend fun getTermsByLectureId(lectureId:String):List<Term>
}