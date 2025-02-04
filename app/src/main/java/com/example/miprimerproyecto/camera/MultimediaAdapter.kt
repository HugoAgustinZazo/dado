package com.example.miprimerproyecto.camera

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimerproyecto.R
    class MultimediaAdapter(
        private val listaUris: MutableList<Pair<Uri, String>>,
        private val onDeleteClick: (Uri) -> Unit
    ) : RecyclerView.Adapter<MultimediaAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textViewRuta: TextView = view.findViewById(R.id.textViewRuta)
            val deleteButton: Button = view.findViewById(R.id.deleteButton)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_multimedia, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val (uri, _) = listaUris[position]

            holder.textViewRuta.text = uri.toString()
            holder.deleteButton.setOnClickListener { onDeleteClick(uri) }
        }
        override fun getItemCount(): Int = listaUris.size
    }
