package com.example.contestapp.screen.lecture_detail_screen

import android.graphics.BitmapFactory
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
class LectureDetailViewModel @Inject constructor():ViewModel() {

    private val _lectureDetailUIState = MutableStateFlow(LectureDetailUIState())
    val lectureDetailUIState:StateFlow<LectureDetailUIState> = _lectureDetailUIState

    fun init(id:String){
        viewModelScope.launch {
            _lectureDetailUIState.value = lectureDetailUIState.value.copy(isLoading = true)
            val lecture = Repositories.lectureRepository().getLectures().find { it.id == id }!!
            _lectureDetailUIState.value = lectureDetailUIState.value.copy(isLoading = false, lecture = lecture)
            val bytes = Repositories.lectureRepository().getLectureImage(id).data ?: return@launch
            val bmp = BitmapFactory.decodeByteArray(bytes, 0 , bytes.size) ?: return@launch
            _lectureDetailUIState.value = lectureDetailUIState.value.copy(image = bmp.asImageBitmap())
        }
    }


}