package com.balran.usecases.local

import com.balran.data.LocalCocktailsRepository
import com.balran.domain.Drink

class DeleteFavouriteCocktail(private val localCocktailsRepository: LocalCocktailsRepository) {
    suspend fun invoke(drink:Drink){localCocktailsRepository.deleteFavouriteCocktail(drink)}
}