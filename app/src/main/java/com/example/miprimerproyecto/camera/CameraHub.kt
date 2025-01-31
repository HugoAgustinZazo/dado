package com.example.miprimerproyecto.camera

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.R
import com.example.miprimerproyecto.databinding.ActivityCameraHubBinding
import com.example.miprimerproyecto.databinding.ActivityFbSinAutBinding
import com.example.miprimerproyecto.firebaseapp.MainActivityFb
import com.squareup.picasso.Picasso

class CameraHub : AppCompatActivity() {
    lateinit var binding: ActivityCameraHubBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraHubBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_camera_hub)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val username = intent.getStringExtra("username")
        val avatar = intent.getStringExtra("avatar")
        Picasso.get().load(avatar).into(binding.avatar)
        binding.userName.setText("Usuario: "+username)
        binding.button13.setText(binding.button13.text.toString()+username)

        binding.button9.setOnClickListener(){
            val intent = Intent(this@CameraHub, CamaraFotos::class.java)
            intent.putExtra("username", username)
            intent.putExtra("avatar",avatar)
            startActivity(intent)
        }

        binding.button13.setOnClickListener(){
            val intent = Intent(this@CameraHub, ListadoMultimedia::class.java)
            intent.putExtra("username", username)
            intent.putExtra("avatar",avatar)
            startActivity(intent)

        }
    }
}