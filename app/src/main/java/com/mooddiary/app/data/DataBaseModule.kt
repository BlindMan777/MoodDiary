package com.mooddiary.app.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideMainDatabase(@ApplicationContext context: Context): MainDataBase {
        return Room.databaseBuilder(
            context,
            MainDataBase::class.java,
            "saved_moods"
        ).fallbackToDestructiveMigration(true).build()
    }

    @Provides
    fun provideYourDao(database: MainDataBase): DAO {
        return database.dao()
    }
}