package com.example.showweather.feature.showweatger.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ErrorModel : Parcelable {

    enum class ErrorType {

        SERVER_FATAL,
        CONNECTION_LOST,
        UNKNOWN
    }
}