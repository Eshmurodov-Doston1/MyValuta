package com.example.myvaluta.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myvaluta.database.entity.SaveSumm

@Dao
interface SaveDao {
    @Insert
    fun saveSumm(saveSumm: SaveSumm)

    @Delete
    fun deleSave(saveSumm: SaveSumm)

    @Query("select*from savesumm")
    fun getSumm():LiveData<List<SaveSumm>>
}