package com.mooddiary.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_moods")
data class SavedMoods(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val mood: String
)

enum class MoodType(val displayName: String) {
    Great("great_mood"),
    Good("good_mood"),
    Neutral("neutral_mood"),
    Bad("bad_mood"),
}
