package com.sundaydavid.fastBite.ui.category

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.sundaydavid.fastBite.database.local.storage.MealDao
import com.sundaydavid.fastBite.database.local.storage.MealDatabase
import com.sundaydavid.fastBite.database.local.storage.getDatabase
import com.sundaydavid.fastBite.model.CategoryModel
import com.sundaydavid.fastBite.remoteDatabase.ApiClient
import com.sundaydavid.fastBite.remoteDatabase.ApiInterface
import com.sundaydavid.fastBite.repository.MainRepository
import com.sundaydavid.fastBite.utility.CategoryMediatorLiveData
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class CategoryMealStatus{LOADING, ERROR, DONE}
class CategoriesModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val database = getDatabase(application)
    private val repository: MainRepository

    //the internal mutable live data
    private val _categoryMeal = MutableLiveData("")

    //the external mutable live data
    val categoryMeal: LiveData<List<CategoryModel>>


    private val _status = MutableLiveData<CategoryMealStatus>()
    val status: LiveData<CategoryMealStatus>
    get() = _status


    init {
        repository = MainRepository(
            ApiClient.getClient,
            database
        )
        getCategoryMeal()

       categoryMeal = Transformations.switchMap(CategoryMediatorLiveData(_categoryMeal)) {
           repository.getMealCategory()
       }

    }

    fun getCategoriesMealLocal(): LiveData<List<CategoryModel>> {
        return categoryMeal
    }


    private fun getCategoryMeal() {

        _status.value = CategoryMealStatus.LOADING


             repository.getCategory().enqueue(object : Callback<CategoryModel> {
                    override fun onResponse(call: Call<CategoryModel>, response: Response<CategoryModel>) {
                        _status.value = CategoryMealStatus.DONE

                        viewModelScope.launch {
                            response.body()!!.let { repository.setMealCategory(it) }

                        }
                    }

                    override fun onFailure(call: Call<CategoryModel>, t: Throwable) {
                        _status.value = CategoryMealStatus.ERROR
                        Log.e("CategoryMeal", t.localizedMessage!!)
                    }
                })



    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CategoriesModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CategoriesModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}