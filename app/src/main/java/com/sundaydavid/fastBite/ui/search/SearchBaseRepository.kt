package com.sundaydavid.fastBite.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sundaydavid.fastBite.model.AlphabetModel
import com.sundaydavid.fastBite.model.CategoryModel
import com.sundaydavid.fastBite.model.SearchModel
import com.sundaydavid.fastBite.remoteDatabase.ApiClient
import com.sundaydavid.fastBite.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchBaseRepository : ViewModel() {

    companion object {
        private var INSTANCE: SearchBaseRepository? = null
        fun getInstance() = INSTANCE
            ?: SearchBaseRepository().also {
                INSTANCE = it
            }
    }
}