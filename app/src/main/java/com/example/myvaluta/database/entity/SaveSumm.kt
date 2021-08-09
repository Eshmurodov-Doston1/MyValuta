package com.example.myvaluta.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SaveSumm (
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    var summ:String,
    var sale:String,
    var buy:String,
    var sumDen:String,
    var date:String
)