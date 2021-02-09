package com.balran.thecocktailhub.ui.dashboard.fragments.dashboard.cocktailrandom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balran.usecases.remote.GetRandomCocktail

class RandomCocktailViewModelFactory(val getRandomCocktail: GetRandomCocktail):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetRandomCocktail::class.java).newInstance(getRandomCocktail)
    }
}