package com.balran.thecocktailhub.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.balran.domain.Drink
import com.balran.domain.DrinkList
import com.balran.thecocktailhub.base.BaseViewHolder
import com.balran.thecocktailhub.databinding.CocktailRowBinding
import com.bumptech.glide.Glide

class CocktailsListAdapter(private val context: Context,
        private val itemClickListener:OnCocktailClickListener):
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var drinkList= listOf<Drink>()

    interface OnCocktailClickListener{
        fun onCocktailClick(drink:Drink)
    }

    fun setDrinkList(drinkList:List<Drink>){
        this.drinkList=drinkList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return CocktailsViewHolder(CocktailRowBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is CocktailsViewHolder -> {
                holder.bind(drinkList[position], position)
            }
        }
    }

    override fun getItemCount(): Int = drinkList.size

    inner class CocktailsViewHolder(private val binding: CocktailRowBinding) :
        BaseViewHolder<Drink>(binding.root) {
        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.image).centerCrop().into(binding.ivCocktail)
            binding.tvIngredients.text = item.instructions
            binding.tvName.text = item.name
            itemView.setOnClickListener { itemClickListener.onCocktailClick(item) }
        }
    }
}