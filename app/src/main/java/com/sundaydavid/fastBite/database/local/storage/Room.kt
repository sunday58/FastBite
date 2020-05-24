package com.sundaydavid.fastBite.database.local.storage

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.sundaydavid.fastBite.model.CategoryModel


@Dao
interface MealDao {
    @Query("select * from CategoryModel ")
    fun getCategory(): LiveData<List<CategoryModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg meals: List<CategoryModel>)
}

@Database(entities = [CategoryModel::class], version = 1)
abstract class MealDatabase: RoomDatabase(){
    abstract val mealDao: MealDao
}
private lateinit var INSTANCE: MealDatabase

        fun getDatabase(context: Context): MealDatabase {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                MealDatabase::class.java,
                "meals").build()
            }
            return INSTANCE
        }