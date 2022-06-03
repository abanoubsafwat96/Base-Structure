package com.example.baseproject.business.repositories.impl

import com.example.baseproject.business.repositories.abstraction.ProfileRepository
import com.example.baseproject.framework.datasources.ProfilesApi
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val profilesApi: ProfilesApi) : ProfileRepository {
    override suspend fun getProfiles() = profilesApi.getProfiles()
    override suspend fun getAlbums(profileId: Int) = profilesApi.getAlbums(profileId)
    override suspend fun getPhotos(albumId: Int) = profilesApi.getPhotos(albumId)
}