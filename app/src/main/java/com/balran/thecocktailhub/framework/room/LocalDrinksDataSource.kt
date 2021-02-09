package com.balran.thecocktailhub.framework.room

import com.balran.data.LocalCocktailsDataSource
import com.balran.domain.Drink
import com.balran.domain.DrinkList
import com.balran.domain.Resource
import com.balran.thecocktailhub.model.toDrinkList
import com.balran.thecocktailhub.model.toEntityModel
import com.balran.thecocktailhub.model.toPresentationModel

class LocalDrinksDataSource(private val appDatabase: AppDatabase):LocalCocktailsDataSource {
    override suspend fun getAllCocktails(): Resource<DrinkList> {
        return Resource.Success(appDatabase.cocktailsDao().getAllCocktails().toDrinkList())
    }

    override suspend fun insertFavouriteCocktail(drink: Drink) {
        Resource.Success(appDatabase.cocktailsDao().insertFavourite(drink.toPresentationModel().toEntityModel()))
    }

    override suspend fun deleteFavouriteCocktail(drink: Drink) {
        Resource.Success(appDatabase.cocktailsDao().deleteFavourite(drink.toPresentationModel().toEntityModel()))
    }

    override suspend fun isCocktailFavorite(drink: Drink): Boolean {
        return appDatabase.cocktailsDao().getCocktailById(drink.idDrink) != null
    }
}