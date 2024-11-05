package com.example.miprimerproyecto

import android.content.Intent
import android.os.Bundle
import android.service.autofill.Dataset
import android.view.View
import android.widget.TextView
import android.widget.Toast
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
    var TextViewss: ArrayList<TextView> = ArrayList()
    var todosSeleccionados: ArrayList<Jugador> = ArrayList()
    var maxRondas = 0
    var opcion = 0
    var jugador = 0
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

         opcion = intent.getIntExtra("OPCION", 0)
         maxRondas = intent.getIntExtra("MAXRONDAS", 0)

        añadirTextViews()

        for(i in 1..TextViewss.size){
            var tv: TextView = TextViewss[i-1]
            formatoTexto(tv)
        }


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
            val rcv: RecyclerView = jugadoress[i-1]
                rellenarRecV(dataset,rcv)

        }


    }

    fun rellenarRecV(dataset: Array<String>, rcv: RecyclerView) {

            rcv.layoutManager = LinearLayoutManager(this)
            rcv.adapter = CustomAdapter(dataset) { nombrejugador->
                onItemSelected(
                    nombrejugador,rcv
                )
            }

    }

    fun onItemSelected(nombreJugador: String, rcv: RecyclerView){
        if (todosSeleccionados.size < jugadoress.size) {
                if(todosSeleccionados.any { it.nombre == nombreJugador }){
                    Toast.makeText(
                        this,
                        "El nombre elegido ya esta en uso, elige otro",
                        Toast.LENGTH_SHORT
                    ).show()
                }else {
                    jugador+=1
                    todosSeleccionados.add(Jugador(nombreJugador, 0, jugador, 0))
                    Toast.makeText(
                        this,
                        "Jugador añadido: " + nombreJugador,
                        Toast.LENGTH_SHORT
                    ).show()
                    rcv.visibility = View.GONE
                }

        }
        if(todosSeleccionados.size == opcion){
            todosSeleccionados.shuffle()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("OPCION", opcion)
            intent.putExtra("MAXRONDAS", maxRondas)
            for(i in 1..opcion){
                intent.putExtra("Jugador"+i, todosSeleccionados[i-1].nombre)
            }
            startActivity(intent)


        }
    }

    fun formatoTexto(tv : TextView){
        tv.textSize = 20F

    }

    fun añadirTextViews(){
        TextViewss.add(binding.textViewjug1)
        TextViewss.add(binding.textViewjug2)
        TextViewss.add(binding.textViewjug3)
        TextViewss.add(binding.textViewjug4)


    }
}