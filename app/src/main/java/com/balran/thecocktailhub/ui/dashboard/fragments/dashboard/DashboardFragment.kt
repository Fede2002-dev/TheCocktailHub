package com.balran.thecocktailhub.ui.dashboard.fragments.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.balran.thecocktailhub.R
import com.balran.thecocktailhub.databinding.FragmentDashboardBinding
import com.balran.thecocktailhub.ui.dashboard.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class DashboardFragment : Fragment() {
    private lateinit var adapter:ViewPagerAdapter
    private val binding:FragmentDashboardBinding by lazy { FragmentDashboardBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        adapter=ViewPagerAdapter(childFragmentManager, lifecycle)
        initViewPager()
        return binding.root
    }

    private fun initViewPager(){
        binding.viewPager.adapter=adapter
        val tabLayoutMediator= TabLayoutMediator(binding.tabLayout, binding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> {tab.setIcon(R.drawable.ic_baseline_shuffle_24)}
                1 -> {tab.setIcon(R.drawable.ic_baseline_search_24)}
                2 -> {tab.setIcon(R.drawable.ic_baseline_favorite_24)}
            }
        }
        tabLayoutMediator.attach()
    }
}