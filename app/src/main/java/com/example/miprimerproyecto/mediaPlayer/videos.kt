package com.example.miprimerproyecto.mediaPlayer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimerproyecto.R
import com.example.miprimerproyecto.databinding.ActivityVideosBinding
import com.squareup.picasso.Picasso
import java.io.File

class videos : AppCompatActivity() {
    private lateinit var binding: ActivityVideosBinding
    private var usuario = ""
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: videoAdapter
    private var listaMultimedia = mutableListOf<Pair<Uri, String>>()
    private var avatar = ""
    private var videos: List<String> = ArrayList()
    private lateinit var player: ExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideosBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_videos)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        usuario = intent.getStringExtra("username") ?: "UsuarioDesconocido"
        binding.textView20.setText(binding.textView20.text.toString()+usuario)
        avatar = intent.getStringExtra("avatar").toString()
        Picasso.get().load(avatar).into(binding.imageView22)
        recyclerView = binding.videos
        val videosDir =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).absolutePath + "/$usuario";
        val videoList = File(videosDir).listFiles()
        Log.d("HUGO", videosDir)

        if (videoList != null && !videoList.isEmpty()) {
            val videoNames = mutableListOf<String>()
            for (video in videoList) {
                Log.d("HUGO", video.name)
                videoNames.add(video.name)
            }

            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.adapter = videoAdapter(videoNames) { videoSelected->
                val selectedVideoPath = videosDir + "/" + videoSelected

                val intent = Intent(this, Reproductor::class.java)
                intent.putExtra("videoPath", selectedVideoPath)
                intent.putExtra("username",usuario)
                intent.putExtra("avatar",avatar)
                startActivity(intent)
            }

        } else {
            Toast.makeText(this, "No hay videos", Toast.LENGTH_SHORT).show()
        }
    }
}