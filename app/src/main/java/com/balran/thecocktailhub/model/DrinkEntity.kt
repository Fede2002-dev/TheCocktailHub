package com.balran.thecocktailhub.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.balran.domain.Drink as DomainDrink
import com.balran.domain.DrinkList as DomainDrinkList

@Entity
data class DrinkEntity(
    @PrimaryKey
    val idDrink:Int,
    @ColumnInfo(name = "drink_name")
    val drinkName:String,
    @ColumnInfo(name = "drink_instructions")
    val drinkInstructions:String,
    @ColumnInfo(name = "drink_image")
    val drinkImage:String,
)

fun DrinkEntity.toDomainModel():DomainDrink=DomainDrink(idDrink, drinkName, drinkInstructions, drinkImage)
fun Drink.toEntityModel():DrinkEntity= DrinkEntity(idDrink, name!!, instructions!!, image!!)
fun List<DrinkEntity>.toDrinkList(): DomainDrinkList = DomainDrinkList(map { it.toDomainModel() })