package com.example.todos.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todos.data.database.RecipeAppDatabase
import com.example.todos.data.entities.Diary
import com.example.todos.repository.DiaryRepository

class DiaryViewModel(application: Application) : AndroidViewModel(application) {

    val getDiary: LiveData<List<Diary>>
    private val repository: DiaryRepository

    init {
        val diaryDao = RecipeAppDatabase.getDatabase(application).diaryDao()
        repository = DiaryRepository(diaryDao)
        getDiary = repository.getDiary()
    }

    fun addDiary(diary: Diary) {
        repository.addDiary(diary)
    }

    fun updateDiary(diary: Diary) {
        repository.updateDiary(diary)
    }

    fun deleteDiary(diary: Diary) {
        repository.deleteDiary(diary)
    }

    fun getDiaryByDate(date: String) : LiveData<Diary> {
        return repository.getDiaryByDate(date)
    }

}