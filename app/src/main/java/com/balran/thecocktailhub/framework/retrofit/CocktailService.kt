package com.balran.thecocktailhub.framework.retrofit

import com.balran.thecocktailhub.model.Drink
import com.balran.thecocktailhub.model.DrinkList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailService {
    @GET("search.php")
    suspend fun getCocktailByName(@Query(value="s")cocktailName:String):DrinkList

    @GET("random.php")
    suspend fun getRandomCocktail(): Response<DrinkList>
}