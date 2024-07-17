package com.example.todos.repository

import androidx.lifecycle.LiveData
import com.example.todos.data.dao.IngredientDao
import com.example.todos.data.entities.Ingredient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IngredientRepository(private val ingredientDao: IngredientDao) {

    fun getAllIngredients() : LiveData<List<Ingredient>> {
        return ingredientDao.getAllIngredients()
    }

    fun addIngredient(ingredient: Ingredient) {
       CoroutineScope(Dispatchers.IO).launch {
           ingredientDao.addIngredient(ingredient)
       }
    }

    fun updateIngredient(ingredient: Ingredient) {
        CoroutineScope(Dispatchers.IO).launch {
            ingredientDao.updateIngredient(ingredient)
        }
    }

    fun deleteIngredient(ingredient: Ingredient) {
        CoroutineScope(Dispatchers.IO).launch {
            ingredientDao.deleteIngredient(ingredient)
        }
    }
}