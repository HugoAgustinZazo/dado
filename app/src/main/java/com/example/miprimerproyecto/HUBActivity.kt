package com.example.miprimerproyecto

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.databinding.ActivityHubactivityBinding
import com.example.miprimerproyecto.databinding.ActivityLoginBinding

class HUBActivity : AppCompatActivity() {
    lateinit var binding: ActivityHubactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHubactivityBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hubactivity)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val username = intent.getStringExtra("username")
        binding.textView9.textSize = 25F
        binding.textView10.textSize = 20F
        binding.textView11.textSize = 20F

        binding.textView10.setText("Usuario: "+username)


        binding.imageView10.setOnClickListener(){
            val intent = Intent(this@HUBActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}