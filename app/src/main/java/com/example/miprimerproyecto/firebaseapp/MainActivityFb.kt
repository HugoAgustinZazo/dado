package com.example.miprimerproyecto.firebaseapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.MainActivity
import com.example.miprimerproyecto.R
import com.example.miprimerproyecto.databinding.ActivityMainBinding
import com.example.miprimerproyecto.databinding.ActivityMainFbBinding
import com.squareup.picasso.Picasso

class MainActivityFb : AppCompatActivity() {
    lateinit var binding: ActivityMainFbBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainFbBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_fb)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val username = intent.getStringExtra("username")
        val avatar = intent.getStringExtra("avatar")
        Picasso.get().load(avatar).into(binding.imageView19)
        binding.textView16.setText(binding.textView16.text.toString()+" "+username)

        binding.button5.setOnClickListener(){
            val intent = Intent(this@MainActivityFb, FbSinAut::class.java)
            intent.putExtra("username", username)
            intent.putExtra("avatar",avatar)
            startActivity(intent)
        }

    }
}