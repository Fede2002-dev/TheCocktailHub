package com.balran.thecocktailhub.model

import android.os.Parcelable
import com.balran.domain.Ingredient
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IngredientModel (val name:String?):Parcelable

fun Ingredient.toIngredientPresentations():IngredientModel=IngredientModel(name)
fun IngredientModel.toIngredientDomain():Ingredient=Ingredient(name)