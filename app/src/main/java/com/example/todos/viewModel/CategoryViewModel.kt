package com.example.todos.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todos.data.database.RecipeAppDatabase
import com.example.todos.data.entities.Category
import com.example.todos.repository.CategoryRepository

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    val getAllCategories: LiveData<List<Category>>
    private val repository: CategoryRepository

    init {
        val categoryDao = RecipeAppDatabase.getDatabase(application).categoryDao()
        repository = CategoryRepository(categoryDao)
        getAllCategories = repository.getAllCategories()
    }

    fun addCategory(category: Category) {
        repository.addCategory(category)
    }

    fun updateCategory(category: Category) {
        repository.updateCategory(category)
    }

    fun deleteCategory(category: Category) {
        repository.deleteCategory(category)
    }

}