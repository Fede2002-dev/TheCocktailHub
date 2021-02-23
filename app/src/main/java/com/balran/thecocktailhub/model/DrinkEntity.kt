package com.balran.thecocktailhub.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.balran.domain.Ingredient
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
    @ColumnInfo(name="ingredients")
    val ingredients:List<Ingredient>
)

fun DrinkEntity.toDomainModel():DomainDrink=DomainDrink(idDrink, drinkName, drinkInstructions, drinkImage, ingredients)
fun Drink.toEntityModel():DrinkEntity= toDomainModel().toEntityModel()
fun DomainDrink.toEntityModel():DrinkEntity= DrinkEntity(idDrink, name!!, instructions!!, image!!, ingredients)
fun List<DrinkEntity>.toDrinkList(): DomainDrinkList = DomainDrinkList(map { it.toDomainModel() })