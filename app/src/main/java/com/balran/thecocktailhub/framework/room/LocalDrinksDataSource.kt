package com.balran.thecocktailhub.framework.room

import com.balran.data.LocalCocktailsDataSource
import com.balran.domain.Drink
import com.balran.domain.DrinkList
import com.balran.domain.Resource
import com.balran.thecocktailhub.model.toDrinkList
import com.balran.thecocktailhub.model.toEntityModel
import com.balran.thecocktailhub.model.toPresentationModel
import kotlin.Exception

class LocalDrinksDataSource(private val appDatabase: AppDatabase):LocalCocktailsDataSource {
    override suspend fun getAllCocktails(): Resource<DrinkList> {
        return Resource.Success(appDatabase.cocktailsDao().getAllCocktails().toDrinkList())
    }

    override suspend fun insertFavouriteCocktail(drink: Drink) {
        try {
            appDatabase.cocktailsDao().insertFavourite(drink.toPresentationModel().toEntityModel())
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override suspend fun deleteFavouriteCocktail(drink: Drink) {
        Resource.Success(appDatabase.cocktailsDao().deleteFavourite(drink.toPresentationModel().toEntityModel()))
    }

    override suspend fun isCocktailFavorite(drink: Drink): Boolean {
        try {
            return appDatabase.cocktailsDao().getCocktailById(drink.idDrink) != null
        }catch (e:Exception){
            e.printStackTrace()
            return false
        }

    }
}