package org.bitbucket.sergey_ivanenko.numberstesttask.main

import android.app.Application
import org.bitbucket.sergey_ivanenko.numberstesttask.BuildConfig
import org.bitbucket.sergey_ivanenko.numberstesttask.numbers.data.CloudModule

class NumbersApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val cloudModule = if (BuildConfig.DEBUG) CloudModule.Debug() else CloudModule.Release()
    }
}