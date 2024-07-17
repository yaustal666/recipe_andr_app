package com.example.todos.repository

import androidx.lifecycle.LiveData
import com.example.todos.data.dao.RecipeDao
import com.example.todos.data.entities.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeRepository(private val recipeDao: RecipeDao) {

    fun getAllRecipes() : LiveData<List<Recipe>> {
        return recipeDao.getAllRecipes()
    }

    fun getRecipeByCategory(categoryId: Int) : LiveData<List<Recipe>> {
        return recipeDao.getCategoryRecipes(categoryId)
    }

    fun getRecipeByDiary(diaryId: Int) : LiveData<List<Recipe>> {
        return recipeDao.getDiaryRecipes(diaryId)
    }

    fun addRecipe(recipe: Recipe) {
        CoroutineScope(Dispatchers.IO).launch{
            recipeDao.addRecipe(recipe)
        }
    }

    fun updateRecipe(recipe: Recipe) {
        CoroutineScope(Dispatchers.IO).launch{
            recipeDao.updateRecipe(recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        CoroutineScope(Dispatchers.IO).launch{
            recipeDao.deleteRecipe(recipe)
        }
    }
}