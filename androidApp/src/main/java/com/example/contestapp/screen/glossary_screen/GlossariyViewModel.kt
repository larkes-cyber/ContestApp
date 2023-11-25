package com.example.contestapp.screen.glossary_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contestapp.di.Repositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GlossariyViewModel @Inject constructor():ViewModel() {

    private val _termsUIState = MutableStateFlow(TermUIState())
    val termsUIState:StateFlow<TermUIState> = _termsUIState

    fun init(id:String){
        viewModelScope.launch {
            _termsUIState.value = TermUIState(isLoading = true)
            _termsUIState.value = TermUIState(terms = Repositories.termRepository().getTermsByLectureId(id))
        }
    }

}