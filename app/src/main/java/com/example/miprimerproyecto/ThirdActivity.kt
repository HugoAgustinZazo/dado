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
    var ganador: String = ""
    var puntosganador = 0

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
        if(ganador.equals("")) {
            binding.ganador.setText("Los jugadores no han obtenido ningun punto")
            binding.textView41.setText("¡¡Partida Terminada!!")
            binding.textView41.textSize = 25F

        } else {
            binding.ganador.setText("Enhorabuena el ganador es: " + ganador + " con " + puntosganador+ " puntos")
            binding.textView41.setText("¡¡Partida Terminada!!")
        }
    }
}