package com.example.bookmyroom


import kotlinx.coroutines.flow.Flow
import javax.net.ssl.SSLEngineResult.Status

interface ConnetivityObservation {

    fun observe(): Flow<Status>

    enum class Status{
        Available,Unavailable,losing,Lost
    }
}