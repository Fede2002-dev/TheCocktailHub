package com.balran.usecases.remote

import com.balran.data.CocktailsRepository
import com.balran.domain.Drink
import com.balran.domain.Resource

class GetRandomCocktail(private val cocktailsRepository: CocktailsRepository) {
    suspend fun invoke(): Resource<Drink> {
        return cocktailsRepository.getRandomCocktail()
    }

}