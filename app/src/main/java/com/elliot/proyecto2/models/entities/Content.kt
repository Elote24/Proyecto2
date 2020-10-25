package com.elliot.proyecto2.models.entities

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity
data class Content (
    @PrimaryKey(autoGenerate = true)
    val id: Long?=null,
    val nombre: String,
    val telefono: String,
    val correo: String,
    val plan: String,
    val categorias:String
){

    override fun equals(other: Any?): Boolean {
        if (other is Content)
            return other.id==this.id
        return false
    }
}