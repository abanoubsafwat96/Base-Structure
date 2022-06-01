package com.example.baseproject.framework.presentation.features.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseproject.business.entities.Album
import com.example.baseproject.business.entities.Profile
import com.example.baseproject.business.usecases.abstraction.ProfileUseCase
import com.example.baseproject.framework.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

@HiltViewModel
class ProfileViewModel @Inject constructor(private val useCase: ProfileUseCase) : ViewModel() {

    private val _profilesDataState: MutableLiveData<DataState<List<Profile>>> = MutableLiveData()
    val profilesDataState: LiveData<DataState<List<Profile>>> get() = _profilesDataState

    private val _albumsDataState: MutableLiveData<DataState<List<Album>>> = MutableLiveData()
    val albumsDataState: LiveData<DataState<List<Album>>> get() = _albumsDataState

    init {
        getProfiles()
    }

    private fun getProfiles() {
        viewModelScope.launch {
            useCase.getProfiles()
                .onStart { _profilesDataState.value = DataState.Loading }
                .catch { _profilesDataState.value = DataState.Failure(it) }
                .collect { _profilesDataState.value = DataState.Success(it) }
        }
    }

    fun getUserAlbums(profileId: Int) {
        viewModelScope.launch {
            useCase.getAlbums(profileId)
                .onStart { _albumsDataState.value = DataState.Loading }
                .catch { _albumsDataState.value = DataState.Failure(it) }
                .collect { _albumsDataState.value = DataState.Success(it) }
        }
    }

}