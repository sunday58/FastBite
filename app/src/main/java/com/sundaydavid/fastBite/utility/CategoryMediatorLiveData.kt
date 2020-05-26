package com.sundaydavid.fastBite.utility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class CategoryMediatorLiveData<T>(query: LiveData<T>) : MediatorLiveData<T?>() {
    init {
        addSource(query) {
            value = query.value
        }
    }
}