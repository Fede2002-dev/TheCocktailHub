package com.balran.data

import com.balran.domain.Drink
import com.balran.domain.DrinkList
import com.balran.domain.Resource

class CocktailsRepository (private val networkCocktailsDataSource:NetworkCocktailsDataSource){

    suspend fun searchCocktailByName(cocktailName: String): Resource<DrinkList> {return networkCocktailsDataSource.searchCocktail(cocktailName)}

    suspend fun getRandomCocktail():Resource<Drink>{
        return networkCocktailsDataSource.getRandomCocktail()}

}

interface NetworkCocktailsDataSource {
    suspend fun searchCocktail(cocktailName:String): Resource<DrinkList>
    suspend fun getRandomCocktail():Resource<Drink>
}
