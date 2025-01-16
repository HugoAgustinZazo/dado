package com.example.miprimerproyecto.appChistes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimerproyecto.CustomAdapter
import com.example.miprimerproyecto.Jugador
import com.example.miprimerproyecto.R
import com.example.miprimerproyecto.UserApiResponse
import com.example.miprimerproyecto.UserApiService
import com.example.miprimerproyecto.databinding.ActivityChistesNorrisBinding
import com.example.miprimerproyecto.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChistesNorris : AppCompatActivity() {
    lateinit var binding: ActivityChistesNorrisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChistesNorrisBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chistes_norris)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val dataset = arrayOf("animal", "career", "celebrity", "dev", "explicit", "fashion", "food",
            "history", "money", "movie", "music", "political", "relgion", "science",
            "sport" ,"travel"
        )
        rellenarRecV(dataset,binding.chistes)
    }
    fun rellenarRecV(dataset: Array<String>, rcv: RecyclerView) {
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = CustomAdapter(dataset) { tipochiste->
            onItemSelected(
                tipochiste
            )
        }

    }

    fun onItemSelected(tipochiste: String){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/jokes/random/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ChistesApiService::class.java)
        val call = service.getChistePorcategoria(tipochiste)
        call.enqueue(object : Callback<ChistesApiResponse> {
            override fun onResponse(call: Call<ChistesApiResponse>, response: Response<ChistesApiResponse>) {
                if (response.isSuccessful) {
                    val chiste = response.body()?.results?.get(0)?.value
                    if (!chiste.isNullOrEmpty()) {
                        binding.textView14.setText(chiste)
                    }
                }else{
                    Log.d("Hugo", response.raw().toString())
                }
            }
            override fun onFailure(call: Call<ChistesApiResponse>, t: Throwable) {
            }
        })
        }
}