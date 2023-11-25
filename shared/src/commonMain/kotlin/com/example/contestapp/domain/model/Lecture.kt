package com.example.contestapp.domain.model

data class Lecture(
    val id:String,
    val descr:List<List<String>>,
    val time:String,
    val title:String,
    val shortDescr:String,
    val author:String,
    val isFavorite:Boolean,
    val subject:String
)