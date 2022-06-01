package com.example.baseproject.business.usecases.impl

import com.example.baseproject.business.repositories.abstraction.ProfileRepository
import com.example.baseproject.business.usecases.abstraction.ProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfileUseCaseImpl @Inject constructor(private val profileRepository: ProfileRepository) :
    ProfileUseCase {
    override suspend fun getProfiles() = flow {
        emit(profileRepository.getProfiles())
    }.flowOn(Dispatchers.IO)

    override suspend fun getAlbums(profileId: Int) = flow {
        emit(profileRepository.getAlbums(profileId))
    }.flowOn(Dispatchers.IO)

}