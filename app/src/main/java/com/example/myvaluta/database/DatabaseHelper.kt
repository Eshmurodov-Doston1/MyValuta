package com.example.myvaluta.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myvaluta.database.dao.CourseDao
import com.example.myvaluta.database.dao.SaveDao
import com.example.myvaluta.database.entity.CourseEntity
import com.example.myvaluta.database.entity.SaveSumm
import javax.inject.Inject

class DatabaseHelper @Inject constructor(
    private val courseDao: CourseDao,
    private val saveDao: SaveDao
) {
    suspend fun insertAllCourse(list: List<CourseEntity>){
        courseDao.insertCourse(list)
    }

    fun getAllCourse():List<CourseEntity>{
        return courseDao.getAllCourse()
    }

    fun insertSave(saveSumm: SaveSumm){
        saveDao.saveSumm(saveSumm)
    }

    fun getSave():LiveData<List<SaveSumm>>{
        return saveDao.getSumm()
    }

    fun deleteSave(saveSumm: SaveSumm){
        return saveDao.deleSave(saveSumm)
    }

}