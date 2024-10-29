package com.example.miprimerproyecto

import android.os.Bundle
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
    var jugadores: ArrayList<Jugador> = ArrayList<Jugador>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        opcion = intent.getIntExtra("OPCION",0)



    }
    private fun acabarPartida() {
        var puntuaciones : TextView = binding.puntuaciones2
        puntuaciones.textSize = 16F
        var mensajepuntos=""
        for(i in 1..opcion){
            mensajepuntos = mensajepuntos.plus("Puntos del "+jugadores[i-1].nombre+": "+jugadores[i-1].puntuacion+"\n\n")
        }
        puntuaciones.setText(mensajepuntos)
        mensajepuntos = ""
        var max = 0
        var ganador = 0
        for(i in 1..opcion){
            if(jugadores[i-1].puntuacion > max){
                max = jugadores[i-1].puntuacion
                ganador = jugadores[i-1].numjugador
            }
        }
        if(ganador==0) {
            binding.ganador.setText("Los jugadores no han obtenido ningun punto")
            binding.textView41.setText("¡¡Partida Terminada!!")

        } else {
            binding.ganador.setText("Enhorabuena el ganador es: " + jugadores[ganador - 1].nombre + " con " + jugadores[ganador - 1].puntuacion + " puntos")
            binding.textView41.setText("¡¡Partida Terminada!!")
        }
    }
}