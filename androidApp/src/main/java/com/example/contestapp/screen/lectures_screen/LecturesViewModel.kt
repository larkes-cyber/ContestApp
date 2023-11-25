package com.example.contestapp.screen.lectures_screen

import android.graphics.BitmapFactory
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contestapp.di.Repositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LecturesViewModel @Inject constructor():ViewModel() {

    private val _lecturesUIStare = MutableStateFlow(LecturesUIState())
    val lecturesUIStare:StateFlow<LecturesUIState> = _lecturesUIStare

    private val _filterUIState = MutableStateFlow(FilterUIState())
    val filterUIState:StateFlow<FilterUIState> = _filterUIState


    fun onSymbs(symbs:String){
        viewModelScope.launch {
            _filterUIState.value = filterUIState.value.copy(symbs = symbs)
            val lectures = Repositories.lectureRepository().searchForLecture(symbs)
            _lecturesUIStare.value = lecturesUIStare.value.copy(lectures = lectures)
        }
    }

    fun switchFilter(filter:Int){
        _filterUIState.value = filterUIState.value.copy(selectedFilter = filter)
    }

    fun getLectures(){
        viewModelScope.launch {
            _lecturesUIStare.value = LecturesUIState(isLoading = true)
            _lecturesUIStare.value = LecturesUIState(lectures =   Repositories.lectureRepository().getLectures())
        }
    }


    fun getLectureImage(id:String, imageState: MutableState<ImageBitmap?>){
        viewModelScope.launch {
            val bytes = Repositories.lectureRepository().getLectureImage(id).data ?: return@launch
            val bmp = BitmapFactory.decodeByteArray(bytes, 0 , bytes.size) ?: return@launch
            imageState.value = bmp.asImageBitmap()
        }
    }



}