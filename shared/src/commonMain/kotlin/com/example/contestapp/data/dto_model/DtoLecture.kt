package com.example.contestapp.data.dto_model

import kotlinx.serialization.Serializable


@Serializable
data class DtoLecture(
    val id:String,
    val shortDescr:String,
    val descr:String,
    val terms:List<DtoLecture>,
)