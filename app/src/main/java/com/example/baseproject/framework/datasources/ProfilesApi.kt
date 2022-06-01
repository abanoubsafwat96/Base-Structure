package com.example.baseproject.framework.datasources

import com.example.baseproject.business.entities.Album
import com.example.baseproject.business.entities.Profile
import com.example.baseproject.framework.utils.Constants.Network.EndPoints.ALBUMS
import com.example.baseproject.framework.utils.Constants.Network.EndPoints.USERS
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfilesApi {
    @GET(USERS)
    suspend fun getProfiles(): List<Profile>

    @GET(ALBUMS)
    suspend fun getAlbums(@Query("userId") profileId: Int): List<Album>
}