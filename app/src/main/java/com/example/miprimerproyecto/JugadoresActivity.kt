package com.example.miprimerproyecto

import android.os.Bundle
import android.service.autofill.Dataset
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimerproyecto.databinding.ActivityJugadoresBinding
import com.example.miprimerproyecto.databinding.ActivityMainBinding

class JugadoresActivity : AppCompatActivity() {
    lateinit var binding: ActivityJugadoresBinding
    var jugadoress: ArrayList<RecyclerView> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJugadoresBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_jugadores)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val opcion = intent.getIntExtra("OPCION", 0)
        val maxRondas = intent.getIntExtra("RONDAS", 0)


        if(opcion ==2){
            jugadoress.add(binding.jug1)
            jugadoress.add(binding.jug2)
            binding.textViewjug3.visibility = View.GONE
            binding.jug3.visibility = View.GONE
            binding.textViewjug4.visibility = View.GONE
            binding.jug4.visibility = View.GONE

        }else if(opcion == 3){
            jugadoress.add(binding.jug1)
            jugadoress.add(binding.jug2)
            jugadoress.add(binding.jug3)
            binding.textViewjug4.visibility = View.GONE
            binding.jug4.visibility = View.GONE

        }else if(opcion == 4){
            jugadoress.add(binding.jug1)
            jugadoress.add(binding.jug2)
            jugadoress.add(binding.jug3)
            jugadoress.add(binding.jug4)


        }

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

        for(i in 1..opcion){
            var rcv: RecyclerView = jugadoress[i-1]
                rellenarRecV(dataset,rcv)

        }
    }

    fun rellenarRecV(dataset: Array<String>, rcv: RecyclerView) {
            val customAdapter = CustomAdapter(dataset)
            rcv.layoutManager = LinearLayoutManager(this)
            rcv.adapter = customAdapter


    }
}