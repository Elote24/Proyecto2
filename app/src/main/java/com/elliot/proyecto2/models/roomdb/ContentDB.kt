package com.elliot.proyecto2.models.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elliot.proyecto2.models.dao.ContentDAO
import com.elliot.proyecto2.models.entities.Content
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Content::class],
    version = 2,
    exportSchema = true
)
abstract class ContentDB : RoomDatabase() {
    abstract fun contentDAO(): ContentDAO

    companion object {
        @JvmStatic
        @Volatile
        private var instance: ContentDB? = null

        @Synchronized
        fun getInstance(context: Context): ContentDB {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    ContentDB::class.java,
                    "content.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                CoroutineScope(Dispatchers.IO).launch {
                    instance?.initDB()
                }
            }

            return instance as ContentDB
        }
    }

    suspend fun initDB() {
        val contentDAO = contentDAO()
        if (contentDAO.getAllContentSync().isEmpty()) {
            contentDAO.insertContent(
                Content(
                    nombre = "Jasiel",
                    telefono = "6673280230",
                    correo ="Jasiel24pf@gmail.com",
                    plan="Estandar",
                    categorias ="terror"
                )
            )
            contentDAO.insertContent(
                Content(
                    nombre = "Ramon",
                    telefono = "6672678329",
                    correo ="Osito@gmail.com",
                    plan="Premium",
                    categorias ="comedia"
                )
            )
        }
    }
}