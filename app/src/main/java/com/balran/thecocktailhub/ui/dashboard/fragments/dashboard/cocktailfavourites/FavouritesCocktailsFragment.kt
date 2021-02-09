package com.balran.thecocktailhub.ui.dashboard.fragments.dashboard.cocktailfavourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.balran.data.LocalCocktailsRepository
import com.balran.domain.Drink
import com.balran.domain.Resource
import com.balran.thecocktailhub.R
import com.balran.thecocktailhub.databinding.FragmentFavouritesCocktailsBinding
import com.balran.thecocktailhub.framework.room.AppDatabase
import com.balran.thecocktailhub.framework.room.LocalDrinksDataSource
import com.balran.thecocktailhub.model.toPresentationModel
import com.balran.thecocktailhub.ui.dashboard.adapter.CocktailsListAdapter
import com.balran.thecocktailhub.utils.Constants
import com.balran.thecocktailhub.utils.hide
import com.balran.thecocktailhub.utils.show
import com.balran.thecocktailhub.utils.showToast
import com.balran.usecases.local.GetFavouritesCocktails
import kotlinx.android.synthetic.main.fragment_cocktail_search.*

class FavouritesCocktailsFragment : Fragment(), CocktailsListAdapter.OnCocktailClickListener {
    private val binding by lazy { FragmentFavouritesCocktailsBinding.inflate(layoutInflater) }
    private val favouritesCocktailsViewModel by activityViewModels<FavouritesCocktailsViewModel> {
        FavouriteCocktailsViewModelFactory(
            GetFavouritesCocktails(
                LocalCocktailsRepository(LocalDrinksDataSource(AppDatabase.getDatabase(requireContext())))
            )
        )
    }
    private val adapter by lazy{CocktailsListAdapter(requireContext(),this)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()

        //getting favourites cocktails
        favouritesCocktailsViewModel.getFavouritesCocktails()
    }

    override fun onResume() {
        super.onResume()
        favouritesCocktailsViewModel.getFavouritesCocktails()
    }

    private fun setupRecyclerView(){
        binding.rvCocktails.layoutManager= LinearLayoutManager(requireContext())
        binding.rvCocktails.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        binding.rvCocktails.adapter=adapter
    }

    private fun setupObservers(){
        favouritesCocktailsViewModel.fetchFavouritesCocktail.observe(viewLifecycleOwner,{
            when(it){
                is Resource.Loading-> {
                    binding.progressCircular.show()
                }
                is Resource.Success-> {
                    binding.progressCircular.hide()
                    if(it.data.drinkList==null || it.data.drinkList!!.isEmpty()){
                        binding.emptyContainer.root.show()
                        return@observe
                    }
                    adapter.setDrinkList(it.data.drinkList!!)
                    binding.emptyContainer.root.hide()
                }
                is Resource.Failure-> {
                    binding.progressCircular.hide()
                    showToast("Something went wrong: ${it.exception.message}")
                }
            }
        })
    }

    override fun onCocktailClick(drink: Drink) {
        val bundle=Bundle()
        bundle.putParcelable(Constants.ARG_DRINK, drink.toPresentationModel())
        findNavController().navigate(R.id.action_dashboardFragment_to_detailsFragment2, bundle)
    }

}