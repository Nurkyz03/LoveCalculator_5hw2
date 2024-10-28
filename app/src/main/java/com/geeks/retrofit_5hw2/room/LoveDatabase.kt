package com.geeks.retrofit_5hw2.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoveEntity::class], version = 1)
abstract class LoveDataBase: RoomDatabase() {

    abstract fun getHistoryDao(): LoveDao
}