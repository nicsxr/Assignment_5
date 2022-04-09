package com.example.assignment_5.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Query("SELECT * from History")
    fun getData(): List<History>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addHistory(history: History)
}