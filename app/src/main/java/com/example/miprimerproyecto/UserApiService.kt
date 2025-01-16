package com.example.miprimerproyecto

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {
    @GET("api/")
    fun getUserImage(): Call<UserApiResponse>

    @GET("api/")
    fun getUserImageByGender(@Query("gender")gender: String): Call<UserApiResponse>


}