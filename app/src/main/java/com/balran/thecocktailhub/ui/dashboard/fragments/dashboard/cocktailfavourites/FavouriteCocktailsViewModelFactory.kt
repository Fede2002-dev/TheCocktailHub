package com.balran.thecocktailhub.ui.dashboard.fragments.dashboard.cocktailfavourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balran.usecases.local.GetFavouritesCocktails

class FavouriteCocktailsViewModelFactory(private val getFavouritesCocktails: GetFavouritesCocktails):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetFavouritesCocktails::class.java).newInstance(getFavouritesCocktails)
    }
}