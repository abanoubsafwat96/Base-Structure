package com.example.baseproject.di

import com.example.baseproject.business.repositories.abstraction.ProfileRepository
import com.example.baseproject.business.repositories.impl.ProfileRepositoryImpl
import com.example.baseproject.business.usecases.abstraction.ProfileUseCase
import com.example.baseproject.business.usecases.impl.ProfileUseCaseImpl
import com.example.baseproject.framework.datasources.ProfilesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProfileModule {

    @Provides
    @Singleton
    fun provideProfileApi(retrofit: Retrofit): ProfilesApi = retrofit.create(ProfilesApi::class.java)

    @Provides
    @Singleton
    fun provideProfileRepository(profilesApi: ProfilesApi): ProfileRepository =
        ProfileRepositoryImpl(profilesApi)

    @Provides
    @Singleton
    fun provideProfileUseCase(profileRepository: ProfileRepository): ProfileUseCase =
        ProfileUseCaseImpl(profileRepository)
}