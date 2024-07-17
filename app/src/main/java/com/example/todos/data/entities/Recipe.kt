package com.example.todos.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "ingredients_id") val ingredients: List<Ingredient>,
    @ColumnInfo(name = "preparation") val preparation: String,
    @ColumnInfo(name = "cooking") val cooking: String,
    @ColumnInfo(name = "serving") val serving: String,
    @ColumnInfo(name = "category_id") val category: Category?,
    @ColumnInfo(name = "calories") val calories: Int,
    @ColumnInfo(name = "time_to_cook") val timeToCook: String,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean
) : Parcelable
