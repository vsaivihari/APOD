package com.vihari.apod.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.vihari.apod.database.ApodsDatabase
import com.vihari.apod.database.DatabaseApod
import com.vihari.apod.domain.ApodProperty
import com.vihari.apod.network.ApodApi
import com.vihari.apod.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.vihari.apod.database.asDomainModel

class ApodsRepository(private val database: ApodsDatabase) {

    val apods: LiveData<List<ApodProperty>> = Transformations.map(database.apodDao.getApods()){
        it.asDomainModel()
    }

    suspend fun refreshApods() {
        withContext(Dispatchers.IO) {

            val apodlist = ApodApi.retrofitService.getProperties().await()
            database.apodDao.insertAll(apodlist.asDatabaseModel())

        }
    }


}

