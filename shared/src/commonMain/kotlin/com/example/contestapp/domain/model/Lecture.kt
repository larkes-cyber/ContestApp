package com.example.contestapp.domain.model

data class Lecture(
    val id:String,
    val descr:String,
    val time:String,
    val title:String,
    val shortDescr:String,
    val author:String,
    val isFavorite:Boolean,
    val subject:String
)