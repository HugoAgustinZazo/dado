package com.example.miprimerproyecto

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView



class CustomAdapter(private val dataSet: Array<String>, private val onClickListener: (String) -> Unit) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var nombre : String = ""


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        init {
            textView = view.findViewById(R.id.textViewPlayer)
        }

        fun render(nombre: String,onClickListener: (String) -> Unit){

            itemView.setOnClickListener {onClickListener(nombre)}
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position]
        var nombreRecogido = dataSet[position]
        viewHolder.render(nombreRecogido,onClickListener)

        // Configurar el clic en el elemento
        //viewHolder.itemView.setOnClickListener {
            //¿QUE PUEDE SER ESO DEL CONTEXT? ==> PRÓXIMOS CAPÍTULOS
            //val context = viewHolder.itemView.context
            //var nombreRecogido = dataSet[position]

            //nombre=nombreRecogido

         //   val text = "Elemento pulsado: ${dataSet[position]}"
           // Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        //}
    }

    override fun getItemCount() = dataSet.size

}

