package com.example.todos.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todos.data.database.RecipeAppDatabase
import com.example.todos.data.entities.Recipe
import com.example.todos.repository.RecipeRepository


class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    val getAllRecipes: LiveData<List<Recipe>>
    private val repository: RecipeRepository

    init {
        val recipeDao = RecipeAppDatabase.getDatabase(application).recipeDao()
        repository = RecipeRepository(recipeDao)
        getAllRecipes = repository.getAllRecipes()
    }

    fun addRecipe(recipe: Recipe) {
       repository.addRecipe(recipe)
    }

    fun updateRecipe(recipe: Recipe) {
        repository.updateRecipe(recipe)
    }

    fun deleteRecipe(recipe: Recipe) {
        repository.deleteRecipe(recipe)
    }

}