package com.example.ngilizcekelimekartlar

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class KelimeViewModel(private val repository: KelimeRepository) : ViewModel() {
    val allKelimeler: LiveData<List<Kelime>> = repository.allKelimeler

    fun insert(kelime: Kelime) = viewModelScope.launch {
        repository.insert(kelime)
    }

    fun delete(kelime: Kelime) {
        viewModelScope.launch {
            repository.delete(kelime)
        }
    }
}

class KelimeViewModelFactory(private val repository: KelimeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KelimeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return KelimeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
