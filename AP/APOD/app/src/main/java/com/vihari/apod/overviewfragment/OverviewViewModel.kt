package com.vihari.apod.overviewfragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.vihari.apod.database.getDatabase
import com.vihari.apod.network.ApodApi
import com.vihari.apod.domain.ApodProperty
import com.vihari.apod.repository.ApodsRepository
import kotlinx.coroutines.*
import java.io.IOException

class OverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val apodRepository = ApodsRepository(getDatabase(application))
    val apodsList = apodRepository.apods

    private val _navigateToSelectedProperty = MutableLiveData<ApodProperty>()
    val navigateToSelectedProperty: LiveData<ApodProperty>
        get() = _navigateToSelectedProperty

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown



    init {
        getApodProperties()
    }


    private fun getApodProperties() {
        viewModelScope.launch {

            try {
                apodRepository.refreshApods()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {

                if(apodsList.value!!.isEmpty())
                {

                    _eventNetworkError.value = true
                }

            }
        }
    }


    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(apodProperty: ApodProperty) {
        _navigateToSelectedProperty.value = apodProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}
