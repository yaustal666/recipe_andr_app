package com.example.todos.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todos.data.entities.Diary

@Dao
interface DiaryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDiary(diary: Diary)

    @Update
    suspend fun updateDiary(diary: Diary)

    @Delete
    suspend fun deleteDiary(diary: Diary)

    @Query("SELECT * FROM diary ORDER BY id")
    fun getDiary(): LiveData<List<Diary>>

    @Query("SELECT * FROM diary WHERE diary.date = :date")
    fun getDiaryByDate(date: String) : LiveData<Diary>
}