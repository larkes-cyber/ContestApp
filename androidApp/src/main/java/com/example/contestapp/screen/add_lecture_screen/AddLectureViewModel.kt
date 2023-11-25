package com.example.contestapp.screen.add_lecture_screen

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contestapp.di.Repositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddLectureViewModel @Inject constructor():ViewModel(){

    private val _addLectureUIState = MutableStateFlow(AddLectureUIState())
    val addLectureUIState:StateFlow<AddLectureUIState> = _addLectureUIState


    fun uploadFile(file: Uri){
        _addLectureUIState.value = addLectureUIState.value.copy(uploadedFile = file)
    }

    fun changeAuthor(author: String){
        _addLectureUIState.value = addLectureUIState.value.copy(
            author = author
        )
    }

    fun changeTitle(title:String){
        _addLectureUIState.value = addLectureUIState.value.copy(
            title = title
        )
    }

    fun changeSubject(subject:String){
        _addLectureUIState.value = addLectureUIState.value.copy(
            subject = subject
        )
    }

    fun uploadAudio(uri:Uri){
        _addLectureUIState.value = addLectureUIState.value.copy(
            uploadedAudio = uri
        )
    }



    fun onDone(context: Context){
        viewModelScope.launch {
            _addLectureUIState.value = addLectureUIState.value.copy(error = "", loadingHasStarted = true)

            if(
                addLectureUIState.value.uploadedAudio == null
                || addLectureUIState.value.uploadedFile == null
                || addLectureUIState.value.title.isEmpty()
                || addLectureUIState.value.author.isEmpty()
                || addLectureUIState.value.subject.isEmpty()
                ){
                _addLectureUIState.value = addLectureUIState.value.copy(error = "Не корректно заполены поля", loadingHasStarted = false)
                return@launch
            }



            val inputStream:ByteArray = context.getContentResolver().openInputStream(addLectureUIState.value.uploadedAudio!!)!!.readBytes()
            val imgInputStream:ByteArray = context.getContentResolver().openInputStream(addLectureUIState.value.uploadedFile!!)!!.readBytes()

            val id =  Repositories.lectureRepository().addNewLecture(
                subject = addLectureUIState.value.subject,
                title = addLectureUIState.value.title,
                byteArray = inputStream,
                author = addLectureUIState.value.author
            )
            if(id.data != null) Repositories.lectureRepository().updateLecturePhoto(id.data!!, imgInputStream)
            _addLectureUIState.value = addLectureUIState.value.copy(hasBeenDone = true, loadingHasStarted = false)
        }
    }



}