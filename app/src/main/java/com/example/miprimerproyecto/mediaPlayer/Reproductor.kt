package com.example.miprimerproyecto.mediaPlayer

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.miprimerproyecto.R
import com.example.miprimerproyecto.databinding.ActivityReproductorBinding

class Reproductor : AppCompatActivity() {
    private lateinit var binding: ActivityReproductorBinding
    private var uri = ""
    private var usuario = ""
    private var avatar = ""
    private var isPlaying = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReproductorBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reproductor)
        setContentView(binding.root)

        uri = intent.getStringExtra("videoPath").toString()
        usuario = intent.getStringExtra("username").toString()
        avatar = intent.getStringExtra("avatar").toString()
        val videoView = binding.playerView

        videoView.apply {
            setVideoPath(uri)
            setOnPreparedListener {
                it.isLooping = true
                it.start()
            }

        }
        binding.button14.setOnClickListener(){
            if (isPlaying) {
                videoView.pause()
                binding.button14.text = "Reanudar"
            } else {
                videoView.start()
                binding.button14.text = "Pausar"
            }
            isPlaying = !isPlaying

        }
        binding.buttonBack.setOnClickListener(){
            finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        val videoView = binding.playerView
        videoView.stopPlayback()
        videoView.suspend()

    }
    }
