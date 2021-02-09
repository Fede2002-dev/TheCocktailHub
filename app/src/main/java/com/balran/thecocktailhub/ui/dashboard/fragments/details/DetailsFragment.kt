package com.balran.thecocktailhub.ui.dashboard.fragments.details

import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.balran.data.LocalCocktailsRepository
import com.balran.thecocktailhub.R
import com.balran.thecocktailhub.databinding.FragmentDetailsBinding
import com.balran.thecocktailhub.framework.room.AppDatabase
import com.balran.thecocktailhub.framework.room.LocalDrinksDataSource
import com.balran.thecocktailhub.model.Drink
import com.balran.thecocktailhub.model.DrinkEntity
import com.balran.thecocktailhub.model.toDomainModel
import com.balran.thecocktailhub.model.toEntityModel
import com.balran.thecocktailhub.utils.Constants
import com.balran.usecases.local.DeleteFavouriteCocktail
import com.balran.usecases.local.InsertFavouriteCocktail
import com.balran.usecases.local.IsCocktailFavourite
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var drink: Drink

    private val detailsFragmentViewModel by viewModels<DetailsFragmentViewModel> {
        val repository=LocalCocktailsRepository(LocalDrinksDataSource(AppDatabase.getDatabase(requireContext())))
        DetailsFragmentViewModelFactory(
            InsertFavouriteCocktail(repository),
            DeleteFavouriteCocktail(repository),
            IsCocktailFavourite(repository)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable(Constants.ARG_DRINK)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(drink.image).centerCrop().into(binding.ivDrink)
        binding.cocktailTitle.text = drink.name
        binding.cocktailDesc.text = drink.instructions


        binding.fabAddDeleteFav.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                detailsFragmentViewModel.saveOrDeleteCocktail(drink)
                drink.isFav = !drink.isFav
                updateButtonIcon()
            }
        }

        CoroutineScope(Dispatchers.IO).launch(Dispatchers.IO) {
            drink.isFav = detailsFragmentViewModel.isCocktailFavourite(drink)
            updateButtonIcon()
        }
    }

    private fun updateButtonIcon(){
        binding.fabAddDeleteFav.setImageResource(
            when {
                drink.isFav -> R.drawable.ic_baseline_delete_24
                else -> R.drawable.ic_baseline_favorite_white
            })
    }
}