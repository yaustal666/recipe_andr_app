package com.example.todos.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todos.data.entities.Recipe

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecipe(recipe: Recipe)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe ORDER BY id")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * From recipe WHERE id IN(:diaryId) ORDER BY id")
    fun getDiaryRecipes(diaryId: Int): LiveData<List<Recipe>>

    @Query("SELECT * From recipe WHERE id IN(:categoryId) ORDER BY id")
    fun getCategoryRecipes(categoryId: Int): LiveData<List<Recipe>>
}