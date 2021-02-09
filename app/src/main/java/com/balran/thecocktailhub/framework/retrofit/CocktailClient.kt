package com.balran.thecocktailhub.framework.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CocktailClient {
    val cocktailService by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(CocktailService::class.java)
    }
}