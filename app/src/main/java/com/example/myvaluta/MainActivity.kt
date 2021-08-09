package com.example.myvaluta

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.myvaluta.databinding.ActivityMainBinding
import com.example.myvaluta.utils.Status
import com.example.myvaluta.viewmodels.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding:ActivityMainBinding
    lateinit var sharedPreferences:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        sharedPreferences = getSharedPreferences("night",0)
        val boolean = sharedPreferences.getBoolean("my_night", false)
        if(boolean){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onNavigateUp(): Boolean {
        return findNavController(R.id.fragment).navigateUp()
    }
}