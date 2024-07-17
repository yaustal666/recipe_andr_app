package com.example.todos.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Ingredient(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "name") val name: String,
) : Parcelable
