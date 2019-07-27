package com.vihari.apod.network

import androidx.lifecycle.Transformations.map
import com.squareup.moshi.JsonClass
import com.vihari.apod.database.DatabaseApod
import com.vihari.apod.domain.ApodProperty



@JsonClass(generateAdapter = true)
data class NetworkApodContainer(val apods: List<NetworkApod>)




@JsonClass(generateAdapter = true)
data class NetworkApod(val title: String, val explanation: String, val url: String, val date: String)


/**
 * Convert Network results to database objects
 */
fun NetworkApodContainer.asDatabaseModel(): List<DatabaseApod> {
        return apods.map{
            DatabaseApod(
                date = it.date,
                title = it.title,
                explanation = it.explanation,
                url = it.url)
        }
}

fun NetworkApod.asDatabaseModel(): DatabaseApod {
    return DatabaseApod(
            date = date,
            title = title,
            explanation = explanation,
            url = url)


}

fun NetworkApodContainer.asDomainModel(): List<ApodProperty> {
    return apods.map {
        ApodProperty(
            date = it.date,
            title = it.title,
            explanation = it.explanation,
            url = it.url
        )
    }
}