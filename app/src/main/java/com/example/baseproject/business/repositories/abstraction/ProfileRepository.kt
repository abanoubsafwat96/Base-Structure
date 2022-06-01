package com.example.baseproject.business.repositories.abstraction

import com.example.baseproject.business.entities.Album
import com.example.baseproject.business.entities.Profile

interface ProfileRepository {
    suspend fun getProfiles(): List<Profile>
    suspend fun getAlbums(profileId: Int): List<Album>
}