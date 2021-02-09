package com.balran.usecases.local

import com.balran.data.LocalCocktailsRepository
import com.balran.domain.Drink
import com.balran.domain.Resource

class InsertFavouriteCocktail(private val localCocktailsRepository: LocalCocktailsRepository) {
    suspend fun invoke(drink: Drink){
        localCocktailsRepository.insertFavouriteCocktail(drink)
    }
}