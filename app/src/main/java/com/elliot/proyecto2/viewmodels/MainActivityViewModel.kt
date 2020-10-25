package com.elliot.proyecto2.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.elliot.proyecto2.models.entities.Content
import com.elliot.proyecto2.repositories.ContentRepository
import kotlinx.coroutines.launch

class MainActivityViewModel (application: Application) : AndroidViewModel(application) {
    private val contentRepository = ContentRepository(application)
    private val TAG = MainActivityViewModel::class.java.name
    private val insertLiveData = MutableLiveData<Boolean>()

    fun insertContent(content: Content) = viewModelScope.launch{
        try {
            contentRepository.insertContent(content)
            insertLiveData.postValue(true)
        }catch(ex: Exception){
            Log.e(TAG,ex.message,ex)
            insertLiveData.postValue(false)
        }
    }
    fun notifyInsertContent() : LiveData<Boolean> = insertLiveData

    fun getAllContent() : LiveData<List<Content>> = liveData {
        emit(contentRepository.getAllContents())
    }
}