package com.example.todos.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todos.data.database.RecipeAppDatabase
import com.example.todos.data.entities.Ingredient
import com.example.todos.repository.IngredientRepository


class IngredientViewModel(application: Application) : AndroidViewModel(application) {

    val getAllIngredients: LiveData<List<Ingredient>>
    private val repository: IngredientRepository

    init {
        val ingredientDao = RecipeAppDatabase.getDatabase(application).ingredientDao()
        repository = IngredientRepository(ingredientDao)
        getAllIngredients = repository.getAllIngredients()
    }

    fun addIngredient(ingredient: Ingredient) {
        repository.addIngredient(ingredient)
    }

    fun updateIngredient(ingredient: Ingredient) {
        repository.updateIngredient(ingredient)
    }

    fun deleteIngredient(ingredient: Ingredient) {
        repository.deleteIngredient(ingredient)
    }
}