package com.example.miprimerproyecto

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.appChistes.ChistesNorris
import com.example.miprimerproyecto.databinding.ActivityHubactivityBinding
import com.example.miprimerproyecto.databinding.ActivityLoginBinding
import com.example.miprimerproyecto.firebaseapp.FbSinAut
import com.example.miprimerproyecto.firebaseapp.MainActivityFb
import com.squareup.picasso.Picasso

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
        val avatar = intent.getStringExtra("avatar")
        Picasso.get().load(avatar).into(binding.imageView13)

        binding.textView9.textSize = 25F
        binding.textView10.textSize = 20F
        binding.textView11.textSize = 20F
        binding.textView10.setText("Usuario: "+username)


        binding.imageView21.setOnClickListener(){
            val intent = Intent(this@HUBActivity, MainActivityFb::class.java)
            startActivity(intent)

        }
        binding.imageView18.setOnClickListener(){
            val intent = Intent(this@HUBActivity, MainActivityFb::class.java)
            intent.putExtra("username", username)
            intent.putExtra("avatar",avatar)

            startActivity(intent)

        }
        binding.imageView10.setOnClickListener(){
            val intent = Intent(this@HUBActivity, MainActivity::class.java)
            startActivity(intent)
        }
        binding.imageView17.setOnClickListener(){
            val intent = Intent(this@HUBActivity,ChistesNorris::class.java)
            startActivity(intent)
        }
    }
}