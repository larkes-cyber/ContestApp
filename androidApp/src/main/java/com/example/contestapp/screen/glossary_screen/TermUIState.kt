package com.example.contestapp.screen.glossary_screen

import com.example.contestapp.domain.model.Term

data class TermUIState(
    val isLoading:Boolean = false,
    val terms:List<Term> = listOf()
)