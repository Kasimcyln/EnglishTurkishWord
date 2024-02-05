package com.example.ngilizcekelimekartlar

import androidx.lifecycle.LiveData

class KelimeRepository(private val kelimeDao: KelimeDao) {
    val allKelimeler: LiveData<List<Kelime>> = kelimeDao.getAllKelimeler()

    suspend fun insert(kelime: Kelime) {
        kelimeDao.insertKelime(kelime)
    }
    suspend fun delete(kelime: Kelime) {
        kelimeDao.deleteKelime(kelime)
    }

}
