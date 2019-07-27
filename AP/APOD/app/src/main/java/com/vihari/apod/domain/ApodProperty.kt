package com.vihari.apod.domain

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
data class  ApodProperty(val date: String, val title: String, val explanation: String, val url: String): Parcelable