package com.mooddiary.app.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedMoodsRepository @Inject constructor(
    private val dao: DAO
) {
    fun getAllMoods(): Flow<List<SavedMoods>> = dao.getAllItems()

    suspend fun insertMood(mood: SavedMoods) = dao.insertItem(mood)

    suspend fun deleteMood(mood: SavedMoods) = dao.deleteItem(mood)

    suspend fun getMoodByDate(date: String): SavedMoods? = dao.getMoodByDate(date)
}