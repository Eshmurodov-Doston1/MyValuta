package com.example.myvaluta.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myvaluta.database.entity.CourseEntity

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(list: List<CourseEntity>)

    @Query("select*from courseentity")
    fun getAllCourse():List<CourseEntity>
}