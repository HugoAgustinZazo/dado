package com.example.miprimerproyecto

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.databinding.ActivityJugadoresBinding
import com.example.miprimerproyecto.databinding.ActivitySecondBinding
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    var ronda = 1
    var jugador = 0
    var jugadores: ArrayList<Jugador> = ArrayList<Jugador>()
    var opcion = 0
    var maxRondas = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
         opcion = intent.getIntExtra("OPCION",0)
         maxRondas = intent.getIntExtra("MAXRONDAS",0)


        for (i in 1..opcion) {
            var name : String = intent.getStringExtra("Jugador"+i).toString()
            jugadores.add(Jugador(name, 0, i, 0))
        }


        val botontirar = binding.button2
        val botonplantarse = binding.button3
        binding.textView4.setText("Numero de Ronda: $ronda")
        binding.textView4.textSize = 25F
        binding.textView2.setText("Turno de " + jugadores[jugador].nombre)
        binding.textView2.textSize = 18F


        botontirar.setOnClickListener {
            ocultarDado()
            var numal: Int = Random.nextInt(1, 7)
            when (numal) {
                1 -> binding.imageView3.visibility = View.VISIBLE
                2 -> binding.imageView4.visibility = View.VISIBLE
                3 -> binding.imageView5.visibility = View.VISIBLE
                4 -> binding.imageView6.visibility = View.VISIBLE
                5 -> binding.imageView7.visibility = View.VISIBLE
                6 -> binding.imageView8.visibility = View.VISIBLE


            }
            binding.textView4.setText("Numero de Ronda: $ronda")
            binding.textView2.setText("Turno de " + jugadores[jugador].nombre)
            binding.textView3.visibility = View.VISIBLE

            if (numal == 1) {
                jugadores[jugador].puntuacionronda = 0
                binding.textView3.setText("El " + jugadores[jugador].nombre + " ha sacado un: $numal Total puntos ronda:" + jugadores[jugador].puntuacionronda)
                Toast.makeText(
                    this@SecondActivity,
                    "OPPS!!! Ha salido un 1 El Jugador " + jugadores[jugador].numjugador + " Pierde su turno y puntos",
                    Toast.LENGTH_SHORT
                ).show()
                cambiarTurno()
                binding.textView3.visibility = View.VISIBLE
            } else {
                jugadores[jugador].puntuacionronda += numal
                binding.textView3.setText("El " + jugadores[jugador].nombre + " ha sacado un: $numal Total puntos ronda: " + jugadores[jugador].puntuacionronda)
            }
            var puntuaciones: TextView = binding.puntuacion
            puntuaciones.textSize = 16F
            var mensajepuntos = ""
            for (i in 1..opcion) {
                mensajepuntos =
                    mensajepuntos.plus("Puntos de " + jugadores[i - 1].nombre + ": " + jugadores[i - 1].puntuacion + "\n\n")
            }
            puntuaciones.setText(mensajepuntos)
            mensajepuntos = ""
            binding.button3.visibility = View.VISIBLE
            if (ronda > maxRondas) {
                binding.button3.visibility = View.GONE
                binding.textView3.visibility = View.GONE
            }

        }


        botonplantarse.setOnClickListener {
            Toast.makeText(
                this@SecondActivity,
                "El Jugador" + jugadores[jugador].numjugador + " Se ha plantado",
                Toast.LENGTH_SHORT
            ).show()
            cambiarTurno()
        }

    }

    fun cambiarTurno() {
        jugadores[jugador].puntuacion += jugadores[jugador].puntuacionronda
        jugadores[jugador].puntuacionronda = 0
        jugador += 1
        if (jugador >= opcion) {
            jugador = 0
            ronda += 1
            binding.textView4.setText("Numero de Ronda: $ronda")
            binding.textView2.setText("${jugadores[jugador].nombre}")
        }
        if (ronda > maxRondas) {
            binding.textView3.visibility = View.GONE
            //acabarPartida()
        }
        binding.textView2.setText("Turno del ${jugadores[jugador].nombre}")
        binding.textView3.visibility = View.GONE
    }


    private fun ocultar() {
        binding.button2.visibility = View.GONE
        binding.textView2.visibility = View.GONE
        binding.button3.visibility = View.GONE
        binding.textView3.visibility = View.GONE
    }

    private fun ocultarDado() {
        binding.imageView3.visibility = View.GONE
        binding.imageView4.visibility = View.GONE
        binding.imageView5.visibility = View.GONE
        binding.imageView6.visibility = View.GONE
        binding.imageView7.visibility = View.GONE
        binding.imageView8.visibility = View.GONE
    }


    private fun mostrar() {
        binding.button2.visibility = View.VISIBLE
        binding.textView2.visibility = View.VISIBLE
        binding.textView3.visibility = View.VISIBLE
    }
}


