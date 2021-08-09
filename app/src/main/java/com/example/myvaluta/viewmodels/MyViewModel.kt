package com.example.myvaluta.viewmodels

import androidx.lifecycle.ViewModel
import com.example.myvaluta.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val appRepository: AppRepository):ViewModel() {
    fun getCourse() = appRepository.getCourse()

    fun getSaveDao() = appRepository.saveDao()
}