package com.example.myvaluta.retrofit

import javax.inject.Inject

class ApiHelper @Inject constructor(private var apiServise: ApiServise){
    suspend fun getCourse() = apiServise.getCourse()
}