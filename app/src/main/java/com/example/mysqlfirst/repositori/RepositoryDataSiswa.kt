package com.example.mysqlfirst.repositori

import com.example.mysqlfirst.apiservice.ServiceApiSiswa
import com.example.mysqlfirst.modeldata.DataSiswa

interface RepositoryDataSiswa{
    suspend fun getDataSiswa(): List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa):retrofit2.Response<Void>
}

