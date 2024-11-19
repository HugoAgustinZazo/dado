package com.example.miprimerproyecto

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    lateinit var binding: ActivityThirdBinding
    var opcion = 0
    var ganador: String = ""
    var puntosganador = 0
    var jugadores: ArrayList<Jugador> = ArrayList<Jugador>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        opcion = intent.getIntExtra("OPCION",0)
        ganador = intent.getStringExtra("GANADOR").toString()
        puntosganador = intent.getIntExtra("PUNTOSGANADOR",0)
        jugadores = intent.getParcelableArrayListExtra<Jugador>("jugadores")!!
        var puntuaciones : TextView = binding.puntuaciones2
        puntuaciones.textSize = 16F
        var mensajepuntos=""
        for(i in 1..opcion){
            mensajepuntos = mensajepuntos.plus("Puntos del "+jugadores[i-1].nombre+": "+jugadores[i-1].puntuacion+"\n\n")
        }
        puntuaciones.setText(mensajepuntos)
        mensajepuntos = ""
        binding.textView41.textSize= 25F
        if(ganador.equals("")) {
            binding.ganador.setText("Los jugadores no han obtenido ningun punto")
            binding.textView41.setText("¡¡Partida Terminada!!")
            binding.textView41.textSize = 25F

        } else {
            binding.ganador.setText("Enhorabuena el ganador es: " + ganador + " con " + puntosganador+ " puntos")
            binding.textView41.setText("¡¡Partida Terminada!!")
        }
    }
    inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }

    inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelable(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelable(key) as? T
    }
}