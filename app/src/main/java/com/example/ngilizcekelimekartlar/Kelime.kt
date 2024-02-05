package com.example.ngilizcekelimekartlar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "kelimeler", indices = [Index(value = ["ingilizce"], unique = true)])
data class Kelime(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "ingilizce") val ingilizce: String,
    @ColumnInfo(name = "turkce") val turkce: String
)
