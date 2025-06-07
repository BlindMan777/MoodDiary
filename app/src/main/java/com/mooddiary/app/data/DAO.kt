package com.mooddiary.app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(savedMoods: SavedMoods)

    @Delete
    suspend fun deleteItem(savedMoods: SavedMoods)

    @Query("SELECT * FROM saved_moods WHERE date = :date LIMIT 1")
    suspend fun getMoodByDate(date: String): SavedMoods?

    @Query("SELECT * FROM saved_moods")
    fun getAllItems(): Flow<List<SavedMoods>>
}
