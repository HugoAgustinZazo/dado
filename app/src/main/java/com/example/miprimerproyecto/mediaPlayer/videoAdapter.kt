package com.example.miprimerproyecto.mediaPlayer

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimerproyecto.R

class videoAdapter (private val videos:  MutableList<Pair<Uri, String>>,
    private val videoSeleccionado:(Uri)->Unit) : RecyclerView.Adapter<videoAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textViewRuta: TextView = view.findViewById(R.id.textViewRuta)
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val (uri, _) = videos[position]
            holder.textViewRuta.text = uri.toString()
            holder.textViewRuta.setOnClickListener(){
                    videoSeleccionado(uri)
            }
        }
        override fun getItemCount(): Int = videos.size
    }
