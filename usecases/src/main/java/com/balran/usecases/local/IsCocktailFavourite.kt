package com.balran.usecases.local

import com.balran.data.LocalCocktailsRepository
import com.balran.domain.Drink

class IsCocktailFavourite(private val localCocktailsRepository: LocalCocktailsRepository) {
    suspend fun invoke(drink:Drink):Boolean=localCocktailsRepository.isCocktailFavorite(drink)
}