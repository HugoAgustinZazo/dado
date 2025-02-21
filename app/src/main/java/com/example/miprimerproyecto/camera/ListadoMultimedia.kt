package com.example.miprimerproyecto.camera

import android.content.ContentUris
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimerproyecto.databinding.ActivityListadoMultimediaBinding

class ListadoMultimedia : AppCompatActivity() {
    private lateinit var binding: ActivityListadoMultimediaBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MultimediaAdapter
    private lateinit var usuario: String
    private var listaMultimedia = mutableListOf<Pair<Uri, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoMultimediaBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        usuario = intent.getStringExtra("username") ?: "UsuarioDesconocido"
        binding.textView19.text = "Fotos/videos de: $usuario"

        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cargarArchivo()
        adapter = MultimediaAdapter(listaMultimedia) { uri -> eliminarArchivo(uri) }
        recyclerView.adapter = adapter
    }

    private fun cargarArchivo() {
        listaMultimedia.clear()
        obtenerimagen_video(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/jpeg")
        obtenerimagen_video(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "video/mp4")
    }

    private fun obtenerimagen_video(contentUri: Uri, tipo: String) {
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

    private fun eliminarArchivo(uri: Uri) {
        contentResolver.delete(uri, null, null)
        listaMultimedia.removeIf { it.first == uri }
        adapter.notifyDataSetChanged()
    }
    }
