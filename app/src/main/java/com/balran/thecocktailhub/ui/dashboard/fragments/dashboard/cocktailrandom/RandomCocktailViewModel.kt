package com.balran.thecocktailhub.ui.dashboard.fragments.dashboard.cocktailrandom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.balran.domain.Drink
import com.balran.domain.Resource
import com.balran.usecases.remote.GetRandomCocktail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomCocktailViewModel(private val getRandomCocktail: GetRandomCocktail):ViewModel() {

    private val _randomCocktailData = MutableLiveData<Resource<Drink>>()
    val randomCocktailData:LiveData<Resource<Drink>> get() = _randomCocktailData

    fun getRandomCocktail(){
        CoroutineScope(Dispatchers.IO).launch {
            _randomCocktailData.postValue(Resource.Loading())
            try {
                _randomCocktailData.postValue(getRandomCocktail.invoke())
            }catch (e:Exception){
                _randomCocktailData.postValue(Resource.Failure(e))
            }
        }
    }

}