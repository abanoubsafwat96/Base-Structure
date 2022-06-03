package com.example.baseproject.framework.utils

interface Constants {
    interface Network {
        object EndPoints {
            const val USERS = "users"
            const val ALBUMS = "albums"
            const val PHOTOS = "photos"
        }
    }

    object Search {
        const val DEBOUNCE_TIME = 500L
    }
}