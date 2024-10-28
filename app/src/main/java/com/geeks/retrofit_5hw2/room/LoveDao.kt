package com.geeks.retrofit_5hw2.room

import android.health.connect.datatypes.units.Percentage
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface LoveDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(LoveEntity: LoveEntity)


    @Insert
    fun addHistory(historyEntity: LoveEntity)

    @Query("SELECT * FROM love_table ORDER BY first_name ASC")
    fun getHistory(): List<LoveEntity>

    @Query("SELECT * FROM love_table ORDER BY id DESC")
    fun getHistoryByIds(): List<LoveEntity>

    @Delete
    fun deleteHistory(historyEntity: LoveEntity)

    @Query("DELETE FROM love_table")
    fun deleteAllHistory()

    @Query("DELETE FROM love_table WHERE id IN (:ids)")
    fun deleteHistoryByIds(ids: List<Int>)

    @Update
    fun updateHistory(loveEntity: LoveEntity)

    @Query("SELECT * FROM love_table WHERE percent > :min OR percent < :max")
    fun getHistoryWherePercentageIsGreaterThan(min: String, max: String): List<LoveEntity>
}