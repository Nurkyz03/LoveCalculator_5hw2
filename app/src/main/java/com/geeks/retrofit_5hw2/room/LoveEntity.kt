package com.geeks.retrofit_5hw2.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "love_table")
data class LoveEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo (name = "second_name") val secondName: String,
    @ColumnInfo (name = "percent") val percentage: String,
){
    companion object {
        const val DEFAULT_ID = 0
    }
}