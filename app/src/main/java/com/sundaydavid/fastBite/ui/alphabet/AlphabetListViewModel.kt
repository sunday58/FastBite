package com.sundaydavid.fastBite.ui.alphabet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sundaydavid.fastBite.model.AlphabetModel
import com.sundaydavid.fastBite.model.Meal
import com.sundaydavid.fastBite.repository.MealRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlphabetListViewModel(mealRepository: MealRepository) : ViewModel() {

    private val repository = mealRepository

    private val _meal = MutableLiveData<ArrayList<Meal>>()
    val meal: LiveData<ArrayList<Meal>> = _meal

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress

    fun setData(query: String) {
        val call: Call<AlphabetModel> = repository.getMeal(query)
        call.enqueue(object : Callback<AlphabetModel> {
            override fun onResponse(call: Call<AlphabetModel>, response: Response<AlphabetModel>) {
                _meal.value = ArrayList(response.body()!!.meals)
                _showProgress.value = false
            }

            override fun onFailure(call: Call<AlphabetModel>, t: Throwable) {
                println("Error " + t.localizedMessage)
                _showProgress.value = false
            }
        })
    }
}

class AlphabetListViewModelFactory(private val mealRepository: MealRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlphabetListViewModel::class.java)) {
            return AlphabetListViewModel(mealRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}