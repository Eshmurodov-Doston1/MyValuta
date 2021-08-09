package com.example.myvaluta.di

import android.content.Context
import androidx.room.Room
import com.example.myvaluta.database.AppDatabase
import com.example.myvaluta.database.dao.CourseDao
import com.example.myvaluta.database.dao.SaveDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context:Context):AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java,"my_db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase):CourseDao{
        if (!appDatabase.isOpen){
            appDatabase.openHelper.writableDatabase
        }
        return appDatabase.courseDao()
    }

    @Provides
    @Singleton
    fun provideSave(appDatabase: AppDatabase):SaveDao{
        if (!appDatabase.isOpen){
            appDatabase.openHelper.writableDatabase
        }
        return appDatabase.saveDao()
    }
}