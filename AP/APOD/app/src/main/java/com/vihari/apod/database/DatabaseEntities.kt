package com.vihari.apod.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vihari.apod.domain.ApodProperty


@Entity
data class DatabaseApod constructor(
    @PrimaryKey
    val date: String,
    val title: String,
    val explanation: String,
    val url: String)


fun List<DatabaseApod>.asDomainModel(): List<ApodProperty> {
    return map {
        ApodProperty(
            date = it.date,
            title = it.title,
            explanation = it.explanation,
            url = it.url
        )
    }
}

fun List<DatabaseApod>.asDatabaseModel(): List<DatabaseApod> {
    return map {
        DatabaseApod(
            title = it.title,
            explanation = it.explanation,
            url = it.url,
            date = it.date
        )
    }
}