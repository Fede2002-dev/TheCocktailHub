package com.balran.usecases.remote

import com.balran.data.CocktailsRepository
import com.balran.domain.DrinkList
import com.balran.domain.Resource

class GetCocktailsByName(private val cocktailsRepository: CocktailsRepository) {
    suspend fun invoke(cocktailName:String):Resource<DrinkList>{
        return cocktailsRepository.searchCocktailByName(cocktailName)
    }
}