package com.example.mysqlfirst.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysqlfirst.modeldata.DetailSiswa
import com.example.mysqlfirst.modeldata.UIStateSiswa
import com.example.mysqlfirst.modeldata.toDataSiswa
import com.example.mysqlfirst.modeldata.toUiStateSiswa
import com.example.mysqlfirst.repositori.RepositoryDataSiswa
import com.example.mysqlfirst.uicontroller.route.DestinasiEdit
import kotlinx.coroutines.launch

class EditViewModel(savedStateHandle: SavedStateHandle, private val repositoryDataSiswa:
RepositoryDataSiswa): ViewModel() {
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiEdit.itemIdArg])
    init {
        viewModelScope.launch {
            uiStateSiswa = repositoryDataSiswa.getSatuSiswa(idSiswa)
                .toUiStateSiswa(true)
        }
    }
    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa ): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telepon.isNotBlank()
        }
    }
    suspend fun editSatuSiswa(){
        if (validasiInput(uiStateSiswa.detailSiswa)){
            repositoryDataSiswa.editSatuSiswa(idSiswa,uiStateSiswa.detailSiswa.toDataSiswa())
        }
    }
}