package com.example.todos.repository

import androidx.lifecycle.LiveData
import com.example.todos.data.dao.CategoryDao
import com.example.todos.data.entities.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryRepository(private val categoryDao: CategoryDao) {

    fun getAllCategories() : LiveData<List<Category>> {
        return categoryDao.getAllCategories()
    }

    fun addCategory(category: Category) {
        CoroutineScope(Dispatchers.IO).launch {
            categoryDao.addCategory(category)
        }
    }

    fun updateCategory(category: Category) {
        CoroutineScope(Dispatchers.IO).launch {
            categoryDao.updateCategory(category)
        }
    }

    fun deleteCategory(category: Category) {
        CoroutineScope(Dispatchers.IO).launch {
            categoryDao.deleteCategory(category)
        }
    }
}