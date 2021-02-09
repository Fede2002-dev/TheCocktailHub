package com.balran.thecocktailhub.framework.retrofit

import com.balran.data.NetworkCocktailsDataSource
import com.balran.domain.Drink
import com.balran.domain.Resource
import com.balran.domain.DrinkList
import com.balran.thecocktailhub.model.toDomainModel
import java.lang.Exception

class RemoteDrinksDataSource : NetworkCocktailsDataSource {

    override suspend fun searchCocktail(cocktailName: String): Resource<DrinkList> {
        return Resource.Success(
            CocktailClient.cocktailService.getCocktailByName(cocktailName)
                .toDomainModel()
        )
    }

    override suspend fun getRandomCocktail(): Resource<Drink> {
        val response = CocktailClient.cocktailService.getRandomCocktail()
        return if (response.isSuccessful) {
            Resource.Success(response.body()?.drinkList?.get(0)?.toDomainModel()!!)
        } else {
            Resource.Failure(Exception(response.message()))
        }
    }
}