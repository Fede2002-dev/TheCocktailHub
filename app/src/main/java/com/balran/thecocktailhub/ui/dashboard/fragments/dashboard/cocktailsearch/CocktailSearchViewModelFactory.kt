package com.balran.thecocktailhub.ui.dashboard.fragments.dashboard.cocktailsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balran.usecases.remote.GetCocktailsByName

class CocktailSearchViewModelFactory(private val getCocktailsByName: GetCocktailsByName):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetCocktailsByName::class.java).newInstance(getCocktailsByName)
    }
}