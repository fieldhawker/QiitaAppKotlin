package com.product.hawker.qiitaappkotlin.service

import android.app.IntentService
import android.content.Intent
import com.google.android.gms.location.LocationResult
import com.product.hawker.qiitaappkotlin.database.insertLocations

class LocationService : IntentService("LocationService") {

    override fun onHandleIntent(intent: Intent?) {

        val result = LocationResult.extractResult(intent)

        if (result != null) {
            insertLocations(this, result.locations)
        }

    }

}