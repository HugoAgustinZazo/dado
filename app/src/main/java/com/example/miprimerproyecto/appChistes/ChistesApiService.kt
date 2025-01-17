package com.example.miprimerproyecto.appChistes


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChistesApiService {

    @GET("jokes/random")
    fun getChistePorCategoria(@Query("categoria")categoria: String): Call<Chistes>
}