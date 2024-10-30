package com.example.miprimerproyecto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView



class CustomAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        init {
            textView = view.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position]

        // Configurar el clic en el elemento
        viewHolder.itemView.setOnClickListener {
            //¿QUE PUEDE SER ESO DEL CONTEXT? ==> PRÓXIMOS CAPÍTULOS
            val context = viewHolder.itemView.context
            val text = "Elemento pulsado: ${dataSet[position]}"
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = dataSet.size

}

