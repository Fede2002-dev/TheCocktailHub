package com.balran.domain

data class Drink (
    val idDrink:Int,
    val name : String?,
    val instructions : String?,
    val image : String?,
    val ingredients:List<Ingredient>
)

data class DrinkList(val drinkList: List<Drink>?)