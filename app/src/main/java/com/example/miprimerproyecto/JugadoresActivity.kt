package com.example.miprimerproyecto

import android.os.Bundle
import android.service.autofill.Dataset
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimerproyecto.databinding.ActivityJugadoresBinding

class JugadoresActivity : AppCompatActivity() {
    lateinit var binding: ActivityJugadoresBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_jugadores)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val opcion = intent.getIntExtra("OPCION", 0)
        val maxRondas = intent.getIntExtra("RONDAS", 0)


        val dataset = arrayOf(
            "Aitor Tilla",
            "Ana Conda",
            "Armando Broncas",
            "Aurora Boreal",
            "Bartolo Mesa",
            "Carmen Mente",
            "Dolores Delirio",
            "Elsa Pato",
            "Enrique Cido",
            "Esteban Dido",
            "Elba Lazo",
            "Fermin Tado",
            "Lola Mento",
            "Luz Cuesta",
            "Margarita Flores",
            "Paco Tilla",
            "Pere Gil",
            "Pío Nono",
            "Salvador Tumbado",
            "Zoila Vaca"
        )
    }

    fun rellenarRecV(dataset: Array<String>, opcion: Int) {

        if (opcion == 2) {

            val customAdapter = CustomAdapter(dataset)
            val recyclerView: RecyclerView = binding.jug1
            val recyclerView2: RecyclerView = binding.jug2

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = customAdapter

            recyclerView2.layoutManager = LinearLayoutManager(this)
            recyclerView2.adapter = customAdapter

        }
    }
}