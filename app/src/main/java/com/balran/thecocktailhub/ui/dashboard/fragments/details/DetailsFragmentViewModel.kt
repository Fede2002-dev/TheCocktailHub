package com.balran.thecocktailhub.ui.dashboard.fragments.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balran.thecocktailhub.model.Drink
import com.balran.thecocktailhub.model.DrinkEntity
import com.balran.thecocktailhub.model.toDomainModel
import com.balran.thecocktailhub.model.toEntityModel
import com.balran.usecases.local.DeleteFavouriteCocktail
import com.balran.usecases.local.GetFavouritesCocktails
import com.balran.usecases.local.InsertFavouriteCocktail
import com.balran.usecases.local.IsCocktailFavourite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsFragmentViewModel(
    private val insertFavouriteCocktail: InsertFavouriteCocktail,
    private val deleteFavouriteCocktail: DeleteFavouriteCocktail,
    private val isCocktailFavourite: IsCocktailFavourite
) : ViewModel() {

    fun saveOrDeleteCocktail(drink:Drink){
        CoroutineScope(Dispatchers.IO).launch {
            if(isCocktailFavourite(drink)){
                deleteFavouriteCocktail.invoke(drink.toDomainModel())
            }else{insertFavouriteCocktail.invoke(drink.toDomainModel())}
        }
    }

    suspend fun isCocktailFavourite(drink: Drink)=isCocktailFavourite.invoke(drink.toDomainModel())
}