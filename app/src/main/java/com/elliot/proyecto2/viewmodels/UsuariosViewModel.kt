package com.elliot.proyecto2.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.elliot.proyecto2.models.entities.Content
import com.elliot.proyecto2.repositories.ContentRepository

class UsuariosViewModel(application: Application) : AndroidViewModel(application){
    private val usuarioRepository = ContentRepository(application)

    fun getContents() : LiveData<List<Content>> = liveData {
        emitSource(usuarioRepository.getAllContentLiveData())
    }
}