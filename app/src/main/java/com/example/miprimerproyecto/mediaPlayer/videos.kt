package com.example.miprimerproyecto.mediaPlayer

import android.content.ContentUris
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimerproyecto.R
import com.example.miprimerproyecto.camera.MultimediaAdapter
import com.example.miprimerproyecto.databinding.ActivityVideosBinding
import com.squareup.picasso.Picasso

class videos : AppCompatActivity() {
    private lateinit var binding: ActivityVideosBinding
    private var usuario = ""
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: videoAdapter
    private var listaMultimedia = mutableListOf<Pair<Uri, String>>()
    private var videos: List<String> = ArrayList()
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
        val avatar = intent.getStringExtra("avatar")
        Picasso.get().load(avatar).into(binding.imageView22)
        recyclerView = binding.videos
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cargarArchivo()
        adapter = videoAdapter(listaMultimedia){video->reproducirVideo(video)}
        recyclerView.adapter = adapter
    }

    private fun cargarArchivo() {
        listaMultimedia.clear()
        obtenervideo(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "video/mp4")
    }

    private fun obtenervideo(contentUri: Uri, tipo: String) {
        val projection = arrayOf(MediaStore.MediaColumns._ID)
        val selection = "${MediaStore.MediaColumns.RELATIVE_PATH} LIKE ?"
        val selectionArgs = arrayOf("%$usuario%")

        contentResolver.query(contentUri, projection, selection, selectionArgs, null)?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val uri = ContentUris.withAppendedId(contentUri, id)
                listaMultimedia.add(Pair(uri, tipo))
            }
        }
    }
    private fun reproducirVideo(uri: Uri) {

    }
}