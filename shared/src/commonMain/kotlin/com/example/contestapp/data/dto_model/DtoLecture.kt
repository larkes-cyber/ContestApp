package com.example.contestapp.data.dto_model

import com.example.contestapp.domain.model.Term
import kotlinx.serialization.Serializable


@Serializable
data class DtoLecture(
    val short_descr:String,
    val terms:List<List<String>>,
    val text:List<List<String>>,
    val id:String
)
