package com.vihari.apod.detailfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vihari.apod.domain.ApodProperty

class DetailViewModel(apodProperty: ApodProperty, app: Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<ApodProperty>()
    val selectedProperty: LiveData<ApodProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = apodProperty
    }
}
