package com.balran.thecocktailhub.ui.dashboard.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.balran.thecocktailhub.ui.dashboard.fragments.dashboard.cocktailsearch.CocktailSearchFragment
import com.balran.thecocktailhub.ui.dashboard.fragments.dashboard.cocktailfavourites.FavouritesCocktailsFragment
import com.balran.thecocktailhub.ui.dashboard.fragments.dashboard.cocktailrandom.RandomCocktailFragment

class ViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                RandomCocktailFragment()
            }
            1->{
                CocktailSearchFragment()
            }
            2->{
                FavouritesCocktailsFragment()
            }
            else->{
                RandomCocktailFragment()
            }
        }
    }
}