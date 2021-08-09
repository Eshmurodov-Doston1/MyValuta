package com.example.myvaluta.di


import com.example.myvaluta.BuildConfig
import com.example.myvaluta.retrofit.ApiServise
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
       var loggingInterceptor =  HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }else{
        OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder().baseUrl("http://cbu.uz/uzc/arkhiv-kursov-valyut/json/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    @Provides
    @Singleton
    fun provideApiServise(retrofit: Retrofit):ApiServise = retrofit.create(ApiServise::class.java)
}