package com.balran.thecocktailhub.ui.dashboard.fragments.dashboard.cocktailrandom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.balran.data.CocktailsRepository
import com.balran.domain.Drink
import com.balran.domain.Resource
import com.balran.thecocktailhub.R
import com.balran.thecocktailhub.databinding.FragmentRandomCocktailBinding
import com.balran.thecocktailhub.framework.retrofit.RemoteDrinksDataSource
import com.balran.thecocktailhub.model.toPresentationModel
import com.balran.thecocktailhub.utils.Constants
import com.balran.thecocktailhub.utils.showToast
import com.balran.usecases.remote.GetRandomCocktail
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.balran.thecocktailhub.utils.setDisabled
import com.balran.thecocktailhub.utils.setEnabled

class RandomCocktailFragment : Fragment() {
    private val binding by lazy { FragmentRandomCocktailBinding.inflate(layoutInflater) }
    private val randomCocktailViewModel by viewModels<RandomCocktailViewModel> {
        RandomCocktailViewModelFactory(
            GetRandomCocktail(CocktailsRepository(RemoteDrinksDataSource()))
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRandomCocktail.setOnClickListener {
            randomCocktailViewModel.fetchRandomCocktail.observe(viewLifecycleOwner, { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        startAnimation();binding.btnRandomCocktail.setDisabled()
                    }
                    is Resource.Success -> {
                        goToDetailsFragment(resource.data)
                    }
                    is Resource.Failure -> {
                        showToast("${resource.exception.message}")
                        binding.btnRandomCocktail.setEnabled()
                        stopAnimation()
                    }
                }
            })
        }
    }

    private fun goToDetailsFragment(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.ARG_DRINK, drink.toPresentationModel())
        findNavController().navigate(R.id.action_dashboardFragment_to_detailsFragment2, bundle)
    }

    private fun startAnimation() {
        val animation: Animation = AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.cocktail_rotate
        )
        animation.repeatCount = Animation.INFINITE
        binding.btnContainer.startAnimation(animation)
    }

    private fun stopAnimation() {
        binding.btnContainer.clearAnimation()
    }

}