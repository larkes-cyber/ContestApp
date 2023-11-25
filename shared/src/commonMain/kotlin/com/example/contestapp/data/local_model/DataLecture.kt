package com.example.contestapp.data.local_model

data class DataLecture(
    val id:String,
    val descr:List<List<String>>,
    val time:Long,
    val title:String,
    val shortDescr:String,
    val author:String,
    var isFavorite:Boolean,
    val subject:String
)