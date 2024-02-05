package com.example.ngilizcekelimekartlar

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface KelimeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertKelime(kelime: Kelime): Long

    @Query("SELECT * FROM kelimeler")
    fun getAllKelimeler(): LiveData<List<Kelime>>

    @Delete
    suspend fun deleteKelime(kelime: Kelime)

}
