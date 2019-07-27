package com.vihari.apod.detailfragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vihari.apod.domain.ApodProperty

class DetailViewModelFactory(
    private val apodProperty: ApodProperty,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(apodProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
