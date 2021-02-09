package com.balran.thecocktailhub.ui.dashboard.fragments.dashboard.cocktailfavourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.balran.domain.DrinkList
import com.balran.domain.Resource
import com.balran.usecases.local.GetFavouritesCocktails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouritesCocktailsViewModel(private val getFavouritesCocktails: GetFavouritesCocktails):ViewModel() {

    private val _fetchFavouritesCocktails=MutableLiveData<Resource<DrinkList>>()
    val fetchFavouritesCocktail:LiveData<Resource<DrinkList>> get()=_fetchFavouritesCocktails

    fun getFavouritesCocktails(){
        CoroutineScope(Dispatchers.IO).launch {
            _fetchFavouritesCocktails.postValue(Resource.Loading())
            try {
                _fetchFavouritesCocktails.postValue(getFavouritesCocktails.invoke())
            } catch (e: Exception) {
                _fetchFavouritesCocktails.postValue(Resource.Failure(e))
            }
        }
    }
}