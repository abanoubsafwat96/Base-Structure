package com.example.baseproject.framework.utils

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
    }

    /**
     * Function to configure how the singleton ImageLoader is created
     * Coil performs best when you have a single ImageLoader that's shared throughout your app.
     * This is because each ImageLoader has its own set of resources.
     * Optionally, you can create your own ImageLoader instance(s) and inject them using a dependency injector like Dagger.
     * If you do that, depend on io.coil-kt:coil-base as that artifact doesn't create the singleton ImageLoader.
     */
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this).components {

        }.build()
    }
}