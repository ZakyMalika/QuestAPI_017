package com.example.mysqlfirst.repositori

import android.app.Application
import com.example.mysqlfirst.apiservice.ServiceApiSiswa
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

interface ContainerApp{
    val repositorySiswa: RepositoryDataSiswa
}

class DefaultContainerApp : ContainerApp{
    private val baseurl= "http://10/0/2/2/umyTI"

    val logging = HttpLoggingInterceptor().apply{
        level = HttpLoggingInterceptor.Level.BODY
    }
    val klien = OkHttpClient.Builder()
        .addInterceptor {logging }
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(
            json{
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            }.asConverterFactory("application/json".toMediaType())
        )
        .client(klien)
        .build(
        )
    private val retrofitService: ServiceApiSiswa by lazy{
        retrofit.create(ServiceApiSiswa::class.java)
    }
    override val repositorySiswa: RepositoryDataSiswa by lazy{
        JaringanRepositoryDataSiswa(retrofitService)
    }
    class AplikasiDataSiswa: Application(){
        lateinit var containerApp: ContainerApp
        override fun onCreate() {
            super.onCreate()
            containerApp = DefaultContainerApp()
        }
    }
}