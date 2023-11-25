package com.example.contestapp.screen.lectures_screen

import com.example.contestapp.domain.model.Lecture

data class LecturesUIState(
    val isLoading:Boolean = false,
    val lectures:List<Lecture> = listOf()
)