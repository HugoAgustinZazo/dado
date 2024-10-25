package com.example.miprimerproyecto

import android.os.Bundle
import android.os.Handler
import android.renderscript.ScriptGroup.Binding
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimerproyecto.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var ronda = 1
    var jugador = 0
    val maxRondas = 5
    var jugadores: ArrayList<Jugador> = ArrayList()
    var opcion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.splash_screen)


        Handler().postDelayed({

            setContentView(R.layout.activity_main)
            setContentView(binding.root)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            binding.textView.setText("Elige el numero de jugadores")
            binding.textView.textSize = 18F
            ocultar()
            ocultarDado()

            val numJugadores: List<Int> = listOf(2, 3, 4)
            var adapter: ArrayAdapter<Int> =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, numJugadores)
            binding.spinner2.adapter = adapter
            binding.spinner2.setSelection(0)
            binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: android.view.View,
                    position: Int,
                    id: Long
                ) {
                    opcion = numJugadores[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }

            val boton = binding.button
            val botontirar = binding.button2
            val botonplantarse = binding.button3


            boton.setOnClickListener {
                for (i in 1..opcion) {
                    jugadores.add(Jugador("Jugador " + i, 0, i, 0))
                }
                Toast.makeText(
                    this@MainActivity,
                    "Numero de jugadores: $opcion",
                    Toast.LENGTH_SHORT
                )
                    .show()
                binding.button.visibility = View.GONE
                binding.spinner2.visibility = View.GONE
                binding.textView.visibility = View.GONE

                mostrar()
                binding.textView.textSize = 25F
                binding.textView4.setText("Numero de Ronda: $ronda")
                binding.textView4.textSize = 25F
                binding.textView2.setText("Turno del " + jugadores[jugador].nombre)
                binding.textView2.textSize = 18F
                binding.imageView9.visibility = View.GONE

            }
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
                binding.textView2.setText("Turno del " + jugadores[jugador].nombre)
                binding.textView3.visibility = View.VISIBLE

                if (numal == 1) {
                    jugadores[jugador].puntuacionronda = 0
                    binding.textView3.setText("El " + jugadores[jugador].nombre + " ha sacado un: $numal Total puntos ronda:" + jugadores[jugador].puntuacionronda)
                    Toast.makeText(
                        this@MainActivity,
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
                            mensajepuntos.plus("Puntos del " + jugadores[i - 1].nombre + ": " + jugadores[i - 1].puntuacion + "\n\n")
                    }
                    puntuaciones.setText(mensajepuntos)
                    mensajepuntos = ""
                    binding.button3.visibility = View.VISIBLE
                if(ronda>5){
                    binding.button3.visibility = View.GONE
                    binding.textView3.visibility = View.GONE
                }

            }


            botonplantarse.setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "El Jugador" + jugadores[jugador].numjugador + " Se ha plantado",
                    Toast.LENGTH_SHORT
                ).show()
                cambiarTurno()
            }

        },3000)}

    fun cambiarTurno() {
        jugadores[jugador].puntuacion+=jugadores[jugador].puntuacionronda
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
            acabarPartida()
        }
        binding.textView2.setText("Turno del ${jugadores[jugador].nombre}")
        binding.textView3.visibility = View.GONE
    }

    private fun acabarPartida() {
        ocultarDado()
        binding.button2.visibility = View.GONE
        binding.button3.visibility = View.GONE
        binding.textView2.visibility = View.GONE
        binding.textView3.visibility = View.GONE
        binding.imageView2.visibility = View.VISIBLE
        var puntuaciones : TextView = binding.puntuacion
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
            binding.textView4.setText("¡¡Partida Terminada!!")
            binding.textView3.visibility = View.GONE
        } else
            binding.ganador.setText("Enhorabuena el ganador es: " + jugadores[ganador - 1].nombre + " con " + jugadores[ganador - 1].puntuacion + " puntos")
            binding.textView4.setText("¡¡Partida Terminada!!")
            binding.textView3.visibility = View.GONE

    }

    private fun ocultar() {
        binding.button2.visibility = View.GONE
        binding.textView2.visibility = View.GONE
        binding.button3.visibility = View.GONE
        binding.textView3.visibility = View.GONE
        binding.imageView2.visibility = View.GONE
    }

    private fun ocultarDado(){
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