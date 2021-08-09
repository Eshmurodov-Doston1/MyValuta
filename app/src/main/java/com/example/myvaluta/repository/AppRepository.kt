package com.example.myvaluta.repository

import androidx.lifecycle.MutableLiveData
import com.example.myvaluta.database.DatabaseHelper
import com.example.myvaluta.database.entity.CourseEntity
import com.example.myvaluta.models.Course
import com.example.myvaluta.retrofit.ApiHelper
import com.example.myvaluta.utils.NetworkHelper
import com.example.myvaluta.utils.Resource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class AppRepository @Inject constructor(
    private var apiHelper:ApiHelper,
    private var networkHelper: NetworkHelper,
    private val databaseHelper: DatabaseHelper
) {


    fun saveDao():DatabaseHelper{
     return databaseHelper
    }

    var listCourse = MutableLiveData<Resource<List<CourseEntity>>>()
    fun getCourse():MutableLiveData<Resource<List<CourseEntity>>>{
            GlobalScope.launch {
                try {
                    if (networkHelper.isNetworkConnected()) {
                        listCourse.postValue(Resource.loading(null))
                        apiHelper.getCourse().let {
                            var listCourse1 = ArrayList<CourseEntity>()
                            it.forEach {
                                listCourse1.add(
                                    CourseEntity(
                                    it.Ccy,
                                    it.CcyNm_EN,
                                    it.CcyNm_RU,
                                    it.CcyNm_UZ,
                                    it.CcyNm_UZC,
                                    it.Code,
                                    it.Date,
                                    it.Diff,
                                    it.Nominal,
                                    it.Rate,
                                    it.id
                                )
                                )
                            }
                            listCourse.postValue(Resource.success(listCourse1))
                            databaseHelper.insertAllCourse(listCourse1)
                        }
                    }else{
                        if (databaseHelper.getAllCourse().isNotEmpty()){
                            listCourse.postValue(Resource.success(databaseHelper.getAllCourse()))
                        }else{
                            listCourse.postValue(Resource.error("Information",null))
                        }
                    }
                }catch (e:Exception){
                 listCourse.postValue(Resource.error(e.message?:"Error",null))
                }
            }
        return listCourse
    }
}