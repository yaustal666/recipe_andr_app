package com.example.todos.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todos.data.Converters
import com.example.todos.data.dao.CategoryDao
import com.example.todos.data.dao.DiaryDao
import com.example.todos.data.dao.IngredientDao
import com.example.todos.data.dao.RecipeDao
import com.example.todos.data.entities.Category
import com.example.todos.data.entities.Diary
import com.example.todos.data.entities.Ingredient
import com.example.todos.data.entities.Recipe

@Database(entities = [Category::class, Ingredient::class, Recipe::class, Diary::class], version = 9, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RecipeAppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun ingredientDao(): IngredientDao

    abstract fun recipeDao(): RecipeDao

    abstract fun diaryDao(): DiaryDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeAppDatabase? = null

        fun getDatabase(context: Context): RecipeAppDatabase {
            val tempInstance = INSTANCE

            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeAppDatabase::class.java,
                    "recipe_app_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }
    }

}