package com.example.baseproject.business.usecases.abstraction

import com.example.baseproject.business.entities.Album
import com.example.baseproject.business.entities.Photo
import com.example.baseproject.business.entities.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileUseCase {
    suspend fun getProfiles(): Flow<List<Profile>>
    suspend fun getAlbums(profileId: Int): Flow<List<Album>>
    suspend fun getPhotos(albumId: Int): Flow<List<Photo>>
}