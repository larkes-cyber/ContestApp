package com.example.contestapp.screen.lecture_detail_screen

import androidx.compose.ui.graphics.ImageBitmap
import com.example.contestapp.domain.model.Lecture

data class LectureDetailUIState(

    val isLoading:Boolean = false,
    val lecture:Lecture? = null,
    val image: ImageBitmap? = null,

)