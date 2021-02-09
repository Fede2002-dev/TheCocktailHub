package com.balran.thecocktailhub.ui.dashboard.fragments.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balran.usecases.local.DeleteFavouriteCocktail
import com.balran.usecases.local.InsertFavouriteCocktail
import com.balran.usecases.local.IsCocktailFavourite

class DetailsFragmentViewModelFactory(
    private val insertFavouriteCocktail: InsertFavouriteCocktail,
    private val deleteFavouriteCocktail: DeleteFavouriteCocktail,
    private val isCocktailFavourite: IsCocktailFavourite,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            InsertFavouriteCocktail::class.java,
            DeleteFavouriteCocktail::class.java,
            IsCocktailFavourite::class.java
        ).newInstance(insertFavouriteCocktail, deleteFavouriteCocktail, isCocktailFavourite)
    }
}