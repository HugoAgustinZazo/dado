package com.example.miprimerproyecto

import android.content.Intent
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
    var maxRondas = 0
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
            binding.textView5.setText("Elige el numero de rondas")
            binding.textView5.textSize = 18F

            //ocultar()
            //ocultarDado()

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




            boton.setOnClickListener {
                for (i in 1..opcion) {
                    jugadores.add(Jugador("Jugador " + i, 0, i, 0))
                }

                val valor = binding.editTextText.text.toString()
                if(valor.isNotEmpty()) {
                    maxRondas = valor.toInt()

                    Toast.makeText(
                        this@MainActivity,
                        "Numero de jugadores: $opcion",
                        Toast.LENGTH_SHORT
                    ).show()
                    Toast.makeText(
                        this@MainActivity,
                        "Numero de rondas: $maxRondas",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, JugadoresActivity::class.java)
                    intent.putExtra("OPCION", opcion)
                    intent.putExtra("MAXRONDAS", maxRondas)
                    startActivity(intent)
                }
            }

        },3000)}
}