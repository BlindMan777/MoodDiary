package com.mooddiary.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        SavedMoods::class
    ],
    version = 4
)
abstract class MainDataBase : RoomDatabase() {

    abstract fun dao(): DAO

    companion object {
        fun createDataBase(context: Context): MainDataBase {
            return Room.databaseBuilder(
                context = context,
                klass = MainDataBase::class.java,
                name = ""
            ).build()
        }
    }
}
