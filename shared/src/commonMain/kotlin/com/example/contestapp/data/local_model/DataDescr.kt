package com.example.contestapp.data.local_model

import kotlinx.serialization.Serializable

@Serializable
data class DataDescr(
    val descr:List<List<String>>
)