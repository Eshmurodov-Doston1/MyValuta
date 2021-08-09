package com.example.myvaluta.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myvaluta.database.dao.CourseDao
import com.example.myvaluta.database.dao.SaveDao
import com.example.myvaluta.database.entity.CourseEntity
import com.example.myvaluta.database.entity.SaveSumm

@Database(entities = [CourseEntity::class,SaveSumm::class],version =1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun courseDao():CourseDao
    abstract fun saveDao():SaveDao
}