package com.example.todos.repository

import androidx.lifecycle.LiveData
import com.example.todos.data.dao.DiaryDao
import com.example.todos.data.entities.Diary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiaryRepository(private val diaryDao: DiaryDao) {

    fun getDiary(): LiveData<List<Diary>> {
        return diaryDao.getDiary()
    }

    fun addDiary(diary: Diary) {
        CoroutineScope(Dispatchers.IO).launch {
            diaryDao.addDiary(diary)
        }
    }

    fun updateDiary(diary: Diary) {
        CoroutineScope(Dispatchers.IO).launch {
            diaryDao.updateDiary(diary)
        }
    }

    fun deleteDiary(diary: Diary) {
        CoroutineScope(Dispatchers.IO).launch {
            diaryDao.deleteDiary(diary)
        }
    }

    fun getDiaryByDate(date: String): LiveData<Diary> {
        return diaryDao.getDiaryByDate(date)
    }
}