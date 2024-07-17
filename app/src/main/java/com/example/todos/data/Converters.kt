package com.example.todos.data

import androidx.room.TypeConverter
import com.example.todos.data.entities.Category
import com.example.todos.data.entities.Ingredient
import com.example.todos.data.entities.Recipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.sql.Date

class Converters {
    @TypeConverter
    fun jsonToListIngr(value: String): List<Ingredient> {
        val type = object : TypeToken<List<Ingredient>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun ingListToJson(value: List<Ingredient>): String {
        val type = object : TypeToken<List<Ingredient>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun jsonToListRecipe(value: String): MutableList<Recipe> {
        val type = object : TypeToken<MutableList<Recipe>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun recipeListToJson(value: MutableList<Recipe>): String {
        val type = object : TypeToken<MutableList<Recipe>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun categoryToJson(value: Category): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToCategory(value: String): Category {
        return Gson().fromJson(value, Category::class.java)
    }
}