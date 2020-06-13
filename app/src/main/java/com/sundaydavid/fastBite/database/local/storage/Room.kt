package com.sundaydavid.fastBite.database.local.storage

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.sundaydavid.fastBite.model.Category
import com.sundaydavid.fastBite.model.CategoryModel
import com.sundaydavid.fastBite.model.Meal
import com.sundaydavid.fastBite.model.TypeConverter


@Dao
interface MealDao {
    @Query("SELECT * FROM category ")
    fun getCategory(): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( meals: List<Category>)
}

@Database(exportSchema = false,
    entities = [Category::class], version = 3)
abstract class MealDatabase: RoomDatabase(){
    abstract val mealDao: MealDao
}
private lateinit var INSTANCE: MealDatabase

        fun getDatabase(context: Context): MealDatabase {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                MealDatabase::class.java,
                "meals").fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE
        }