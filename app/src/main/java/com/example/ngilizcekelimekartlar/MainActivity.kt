package com.example.ngilizcekelimekartlar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ngilizcekelimekartlar.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var words: List<String>
    private lateinit var kelimeViewModel: KelimeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSave.isEnabled = false
        val kelimeDao = AppDatabase.getDatabase(application).kelimeDao()
        val repository = KelimeRepository(kelimeDao)
        kelimeViewModel = ViewModelProvider(
            this,
            KelimeViewModelFactory(repository)
        )[KelimeViewModel::class.java]

        words = loadWords()


        binding.tvWords.setOnClickListener {
            val randomWord = words.random()
            binding.tvWords.text = formatWord(randomWord)
            binding.buttonSave.isEnabled = true

        }

        binding.buttonSave.setOnClickListener {
            val ingilizceKelime = binding.tvWords.text.toString()
            val turkceKelime = binding.editTextTurkce.text.toString()
            if (turkceKelime.isNotEmpty()) {
                val kelime = Kelime(ingilizce = ingilizceKelime, turkce = turkceKelime)
                kelimeViewModel.insert(kelime)
                binding.editTextTurkce.text.clear()
            } else {
                Toast.makeText(this@MainActivity, "Bos metin girilimez", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonShow.setOnClickListener {
            val intent = Intent(this, ShowWordsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadWords(): List<String> {
        val inputStream = assets.open("words.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))
        return reader.readLines()
    }

    private fun formatWord(word: String): String {
        val indexOfDot = word.indexOf(' ')
        if (indexOfDot != -1 && word.length > indexOfDot + 1) {
            val substring = word.substring(indexOfDot + 1)
            return substring.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }
        return word
    }
}

