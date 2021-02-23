package com.balran.thecocktailhub.ui.dashboard.fragments.dashboard.cocktailsearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.balran.data.CocktailsRepository
import com.balran.domain.Drink
import com.balran.domain.Resource
import com.balran.thecocktailhub.R
import com.balran.thecocktailhub.databinding.FragmentCocktailSearchBinding
import com.balran.thecocktailhub.framework.retrofit.RemoteDrinksDataSource
import com.balran.thecocktailhub.model.toPresentationModel
import com.balran.thecocktailhub.ui.dashboard.adapter.CocktailsListAdapter
import com.balran.thecocktailhub.utils.Constants
import com.balran.thecocktailhub.utils.hide
import com.balran.thecocktailhub.utils.show
import com.balran.thecocktailhub.utils.showToast
import com.balran.usecases.remote.GetCocktailsByName
import kotlinx.android.synthetic.main.fragment_cocktail_search.*

class CocktailSearchFragment : Fragment(), CocktailsListAdapter.OnCocktailClickListener {
    private val cocktailSearchViewModel by viewModels<CocktailSearchViewModel> {
        CocktailSearchViewModelFactory(
            GetCocktailsByName(CocktailsRepository(RemoteDrinksDataSource()))
        )
    }
    private val binding by lazy { FragmentCocktailSearchBinding.inflate(layoutInflater) }
    private val adapter by lazy { CocktailsListAdapter(requireContext(), this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupRecyclerView()
        setupSearchView()
        setupObservers()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rvCocktails.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCocktails.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvCocktails.adapter=adapter
    }

    private fun setupObservers() {
        cocktailSearchViewModel.fetchCocktailData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    binding.emptyContainer.root.hide()
                    progress_circular.show()
                }
                is Resource.Success -> {
                    progress_circular.hide()
                    if (it.data.drinkList ==null) {
                        binding.emptyContainer.root.show()
                        return@observe
                    }
                    adapter.setDrinkList(it.data.drinkList!!)
                    binding.emptyContainer.root.hide()
                }
                is Resource.Failure -> {
                    showToast("Something has happened: ${it.exception.message}")
                    progress_circular.show()
                    binding.emptyContainer.root.show()
                }
            }
        })
    }

    private fun setupSearchView() {
        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query!!.isNotEmpty()){
                    cocktailSearchViewModel.getCocktails(query.trim())
                    binding.emptySearchContainer.root.hide()
                }else{binding.emptyContainer.root.show()}
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override fun onCocktailClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.ARG_DRINK, drink.toPresentationModel())
        findNavController().navigate(R.id.action_dashboardFragment_to_detailsFragment2, bundle)
    }
}