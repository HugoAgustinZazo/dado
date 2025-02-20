package com.example.miprimerproyecto.juegobola

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.R
import com.example.miprimerproyecto.databinding.ActivityBienvenidaBinding

class Bienvenida : AppCompatActivity() {
    private lateinit var binding:ActivityBienvenidaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBienvenidaBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bienvenida)
        setContentView(binding.root)

    }
}