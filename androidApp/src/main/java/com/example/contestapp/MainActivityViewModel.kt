package com.example.contestapp

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
):ViewModel() {

    fun initKoin(context: Context){
        try {
            BusinessModule(context).init()
        }catch (e:Exception){

        }
    }

}