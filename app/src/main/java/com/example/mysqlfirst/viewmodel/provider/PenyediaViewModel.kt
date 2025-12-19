package com.example.mysqlfirst.viewmodel.provider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mysqlfirst.repositori.AplikasiDataSiswa
import com.example.mysqlfirst.viewmodel.EntryViewModel
import com.example.mysqlfirst.viewmodel.HomeViewModel

fun CreationExtras.aplikasiDataSiswa(): AplikasiDataSiswa = (
        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as
                AplikasiDataSiswa
        )
object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                aplikasiDataSiswa().container
                    .repositoryDataSiswa
            )
        }
        initializer {
            EntryViewModel(
                aplikasiDataSiswa().container
                    .repositoryDataSiswa
            )
        }
    }
}