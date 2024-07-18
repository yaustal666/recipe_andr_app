package com.example.todos.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Diary(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "recipes_id") val recipes: MutableList<Recipe>,
    @ColumnInfo(name = "calories") var calories: Int
) : Parcelable
