package com.example.miprimerproyecto.camera

import android.content.ContentUris
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimerproyecto.R
import com.example.miprimerproyecto.databinding.ActivityListadoMultimediaBinding

class ListadoMultimedia : AppCompatActivity() {
    private lateinit var binding: ActivityListadoMultimediaBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MultimediaAdapter
    private lateinit var usuario: String
    private var listaMultimedia = mutableListOf<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoMultimediaBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_multimedia)
        setContentView(binding.root)

        usuario = intent.getStringExtra("username").toString()
        binding.textView19.setText(binding.textView19.text.toString()+usuario)
            recyclerView = findViewById(R.id.recyclerView)
            recyclerView.setHasFixedSize(true)
            recyclerView.setItemViewCacheSize(-50)
            recyclerView.isNestedScrollingEnabled = false
            recyclerView.layoutManager = LinearLayoutManager(this)
            cargarMultimedia()
            adapter = MultimediaAdapter(listaMultimedia) { uri -> eliminarArchivo(uri) }
            recyclerView.adapter = adapter
        }

        private fun cargarMultimedia() {

            val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
            } else {
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }

            val projection = arrayOf(MediaStore.Images.Media._ID)
            val selection = "${MediaStore.Images.Media.RELATIVE_PATH} LIKE ?"
            val selectionArgs = arrayOf("%$usuario%")

            contentResolver.query(collection, projection, selection, selectionArgs, null)?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val contentUri = ContentUris.withAppendedId(collection, id)
                    listaMultimedia.add(contentUri)
                }
            }
        }

        private fun eliminarArchivo(uri: Uri) {
            contentResolver.delete(uri, null, null)
            listaMultimedia.remove(uri)
            adapter.notifyDataSetChanged()
        }
    }
