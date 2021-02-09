package com.balran.thecocktailhub.ui.dashboard.fragments.dashboard.cocktailrandom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.balran.domain.Resource
import com.balran.usecases.remote.GetRandomCocktail
import kotlinx.coroutines.Dispatchers

class RandomCocktailViewModel(private val getRandomCocktail: GetRandomCocktail):ViewModel() {
    val fetchRandomCocktail= liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(getRandomCocktail.invoke())
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}