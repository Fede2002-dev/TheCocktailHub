package com.balran.data

import com.balran.domain.Drink
import com.balran.domain.DrinkList
import com.balran.domain.Resource

class LocalCocktailsRepository(private val localCocktailsDataSource:LocalCocktailsDataSource){
    suspend fun getAllCocktails():Resource<DrinkList> = localCocktailsDataSource.getAllCocktails()
    suspend fun insertFavouriteCocktail(drink: Drink)= localCocktailsDataSource.insertFavouriteCocktail(drink)
    suspend fun deleteFavouriteCocktail(drink: Drink)= localCocktailsDataSource.deleteFavouriteCocktail(drink)
    suspend fun isCocktailFavorite(drink: Drink):Boolean=localCocktailsDataSource.isCocktailFavorite(drink)
}

interface LocalCocktailsDataSource{
    suspend fun getAllCocktails():Resource<DrinkList>
    suspend fun insertFavouriteCocktail(drink: Drink)
    suspend fun deleteFavouriteCocktail(drink: Drink)
    suspend fun isCocktailFavorite(drink:Drink):Boolean
}