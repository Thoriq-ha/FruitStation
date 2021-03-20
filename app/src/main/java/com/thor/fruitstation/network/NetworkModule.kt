package com.thor.beritahuapp.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {

//    fun interceptor(): OkHttpClient {
//
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//
//        return OkHttpClient.Builder().addInterceptor(interceptor).build()
//    }

    companion object {
        fun configNetwork(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://fruitstation.000webhostapp.com/FruitStation/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun service(): MenuService {
            return configNetwork().create((MenuService::class.java))
        }
    }
}