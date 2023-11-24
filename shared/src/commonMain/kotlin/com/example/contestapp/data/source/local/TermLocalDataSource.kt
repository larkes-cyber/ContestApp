package com.example.contestapp.data.source.local

import com.example.contestapp.data.local_model.DataTerm

interface TermLocalDataSource {
    suspend fun insertTerm(dataTerm: DataTerm)
    suspend fun deleteTerm(term: DataTerm)
    suspend fun getTermsByLectureId(lectureId:String):List<DataTerm>
}