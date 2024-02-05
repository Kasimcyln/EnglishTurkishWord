package com.example.ngilizcekelimekartlar

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Kelime::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun kelimeDao(): KelimeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Migration nesnesi tanımlanıyor.
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Geçiş işlemleri için gerekli SQL komutları buraya yazılır.
                // Örneğin, eğer yeni bir sütun eklemek istiyorsanız, aşağıdaki gibi ekleyebilirsiniz:
                // database.execSQL("ALTER TABLE kelime ADD COLUMN example_column TEXT NOT NULL DEFAULT ''")
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "kelime_database"
                )
                    // Migration nesnesini databaseBuilder'a ekleyin.
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                // return statement
                instance
            }
        }
    }
}
