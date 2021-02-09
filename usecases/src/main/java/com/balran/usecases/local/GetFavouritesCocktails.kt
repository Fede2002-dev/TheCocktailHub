package com.balran.usecases.local

import com.balran.data.LocalCocktailsRepository
import com.balran.domain.DrinkList
import com.balran.domain.Resource

class GetFavouritesCocktails(private val localCocktailsRepository: LocalCocktailsRepository) {
    suspend fun invoke(): Resource<DrinkList> {
        return localCocktailsRepository.getAllCocktails()
    }
}