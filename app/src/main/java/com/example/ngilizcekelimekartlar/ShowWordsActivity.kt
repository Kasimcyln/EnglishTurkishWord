package com.example.ngilizcekelimekartlar

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class ShowWordsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_words)

        // ViewModel ve Repository nesneleri oluşturun
        val kelimeDao = AppDatabase.getDatabase(application).kelimeDao()
        val repository = KelimeRepository(kelimeDao)
        val kelimeViewModel = ViewModelProvider(this, KelimeViewModelFactory(repository)).get(KelimeViewModel::class.java)

        // RecyclerView ve Adapter ayarlama
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = KelimeListAdapter {
            kelimeViewModel.delete(it)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        // Veritabanındaki verileri gözlemle
        kelimeViewModel.allKelimeler.observe(this) { kelimeler ->
            kelimeler?.let {
                adapter.submitList(it)
            }
        }
    }
}
