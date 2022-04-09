package com.example.assignment_5.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
data class History (
    val run : Double,
    val swim : Double,
    val calories : Double
){
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}