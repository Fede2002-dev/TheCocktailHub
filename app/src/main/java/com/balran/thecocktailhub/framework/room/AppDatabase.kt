package com.balran.thecocktailhub.framework.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.balran.thecocktailhub.model.DrinkEntity

@Database(entities = [DrinkEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cocktailsDao(): CocktailsDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "cocktails_table"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}