package com.example.contestapp.domain.mapper

import com.example.contestapp.data.local_model.DataTerm
import com.example.contestapp.domain.model.Term
import database.TermEntity

fun TermEntity.toDataTerm():DataTerm{
    return DataTerm(
        id = id,
        title = title,
        explanation = explanation,
        lectureId = lectureId
    )
}

fun DataTerm.toTerm():Term{
    return Term(
        id = id,
        title = title,
        explanation = explanation,
        lectureId = lectureId
    )
}

fun Term.toDataTerm():DataTerm{
    return DataTerm(
        id = id,
        title = title,
        explanation = explanation,
        lectureId = lectureId
    )
}