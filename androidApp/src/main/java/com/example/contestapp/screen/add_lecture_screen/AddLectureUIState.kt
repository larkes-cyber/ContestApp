package com.example.contestapp.screen.add_lecture_screen

import android.net.Uri
import java.io.File

data class AddLectureUIState(
    val uploadedFile:Uri? = null,
    val author:String = "",
    val title:String = "",
    val subject:String = "",
    val uploadedAudio:Uri? = null,
    val hasBeenDone:Boolean = false
)