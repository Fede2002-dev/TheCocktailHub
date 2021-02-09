package com.balran.thecocktailhub.ui.dashboard.fragments.dashboard.cocktailsearch

import androidx.lifecycle.*
import com.balran.domain.DrinkList
import com.balran.domain.Resource
import com.balran.usecases.remote.GetCocktailsByName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CocktailSearchViewModel(
    private val getCocktailsByName: GetCocktailsByName
) : ViewModel() {

    private val _fetchCocktailData = MutableLiveData<Resource<DrinkList>>()
    val fetchCocktailData:LiveData<Resource<DrinkList>> get()= _fetchCocktailData

    fun getCocktails(cocktailName:String){
        CoroutineScope(Dispatchers.IO).launch {
            _fetchCocktailData.postValue(Resource.Loading())
            try{
                _fetchCocktailData.postValue(getCocktailsByName.invoke(cocktailName))
            }catch (e:Exception){
                _fetchCocktailData.postValue(Resource.Failure(e))
            }
        }
    }
}