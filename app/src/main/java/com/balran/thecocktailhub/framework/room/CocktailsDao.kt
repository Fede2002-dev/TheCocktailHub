package com.balran.thecocktailhub.framework.room

import androidx.room.*
import com.balran.thecocktailhub.model.DrinkEntity

@Dao
interface CocktailsDao {
    @Query("SELECT * FROM drinkentity")
    suspend fun getAllCocktails():List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(drink:DrinkEntity)

    @Delete
    suspend fun deleteFavourite(drink: DrinkEntity)

    @Query("SELECT * FROM drinkentity WHERE idDrink = :idDrink")
    fun getCocktailById(idDrink: Int): DrinkEntity?
}