package com.example.miprimerproyecto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


/**
 * Esta clase recibe el Array con los meses y hereda de RecyclerView.Adapter
 */
class CustomAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Proporciona una referencia al tipo de vistas que está utilizando
     * (custom ViewHolder)
     * ¿Una clase dentro de una clase? ==> :-)
     * DUDA ==> En este código se usa findViewByID...¿hay opción de usar Binding?
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        init {
            // Define el listener de clicks para la vista de cada elemento textView
            textView = view.findViewById(R.id.textView)
        }
    }

    // Crear nuevas vistas (invocadas por el layout manager-administrador de diseño)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Crea una nueva vista, que define la interfaz de usuario (UI) del elemento de la lista
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Reemplazar el contenido de una vista (invocada por el layout manager-administrador de diseño)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        /**
         * Obtiene el elemento del conjunto de datos en esta posición y reemplaza el
         * contenido de la vista con ese elemento
         */
        viewHolder.textView.text = dataSet[position]

        // Configurar el clic en el elemento
        viewHolder.itemView.setOnClickListener {
            //¿QUE PUEDE SER ESO DEL CONTEXT? ==> PRÓXIMOS CAPÍTULOS
            val context = viewHolder.itemView.context
            val text = "Elemento pulsado: ${dataSet[position]}"
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }

    //Devuelve el tamaño de tu conjunto de datos (invocado por el  layout manager)
    override fun getItemCount() = dataSet.size

}

