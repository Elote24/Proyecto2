package com.elliot.proyecto2.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.elliot.proyecto2.models.entities.Content
import com.elliot.proyecto2.models.roomdb.ContentDB

class ContentRepository(context: Context) {
    private val usuarioDB = ContentDB.getInstance(context)
    private val usuarioDAO = usuarioDB.contentDAO()

    suspend fun insertContent(content: Content) {
        usuarioDAO.insertContent(content)
    }

    suspend fun getAllContents(): List<Content> {
        return usuarioDAO.getAllContentSync()
    }

    fun getAllContentLiveData(): LiveData<List<Content>> {
        return usuarioDAO.getAllContent()
    }
}