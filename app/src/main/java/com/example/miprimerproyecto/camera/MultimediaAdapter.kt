package com.example.miprimerproyecto.camera

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimerproyecto.R

    class MultimediaAdapter(
        private val listaUris: MutableList<Uri>,
        private val onDeleteClick: (Uri) -> Unit
    ) : RecyclerView.Adapter<MultimediaAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imageView: ImageView = view.findViewById(R.id.imageView)
            val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_multimedia, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val uri = listaUris[position]
            holder.imageView.setImageURI(uri)
            holder.deleteButton.setOnClickListener { onDeleteClick(uri) }
        }

        override fun getItemCount(): Int = listaUris.size
    }
