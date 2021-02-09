package com.balran.thecocktailhub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import com.balran.domain.Drink as DomainDrink
import com.balran.domain.DrinkList as DomainDrinkList

@Parcelize
data class Drink (
    @SerializedName("idDrink")
    val idDrink:Int,
    @SerializedName("strDrink")
    val name : String?,
    @SerializedName("strInstructions")
    val instructions : String?,
    @SerializedName("strDrinkThumb")
    val image : String?,
    var isFav:Boolean=false
):Parcelable

data class DrinkList(
    @SerializedName("drinks")
    val drinkList: List<Drink>?)

//Conversion functions
fun DomainDrink.toPresentationModel():Drink= Drink(idDrink,name, instructions, image)
fun DomainDrinkList.toPresentationModel():DrinkList= DrinkList(drinkList?.map { it.toPresentationModel() })

fun Drink.toDomainModel():DomainDrink= DomainDrink(idDrink,name, instructions, image)
fun DrinkList.toDomainModel():DomainDrinkList= DomainDrinkList(drinkList?.map { it.toDomainModel() })