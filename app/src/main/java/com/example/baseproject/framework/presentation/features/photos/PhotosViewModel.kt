package com.example.baseproject.framework.presentation.features.photos

import androidx.lifecycle.*
import com.example.baseproject.business.entities.Photo
import com.example.baseproject.business.usecases.abstraction.ProfileUseCase
import com.example.baseproject.framework.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val useCase: ProfileUseCase
) : ViewModel() {

    private var albumId: Int? = stateHandle.get<Int>("albumId")

    private val allPhotosDataState: MutableLiveData<List<Photo>> = MutableLiveData()

    private val _photosDataState: MutableLiveData<DataState<List<Photo>>> = MutableLiveData()
    val photosDataState: LiveData<DataState<List<Photo>>> get() = _photosDataState

    var searchTxtLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            albumId?.let { id ->
                useCase.getPhotos(id)
                    .onStart { _photosDataState.value = DataState.Loading }
                    .catch { _photosDataState.value = DataState.Failure(it) }
                    .collect {
                        _photosDataState.value = DataState.Success(it)
                        allPhotosDataState.value = it
                    }
            }
        }
    }

    fun search(query: String) {
        if (query.isEmpty())
            allPhotosDataState.value?.let { _photosDataState.value = DataState.Success(it) }
        else {
            val searchList = allPhotosDataState.value?.filter { it.title.startsWith(query) }?.toList()
            searchList?.let { _photosDataState.value = DataState.Success(it) }
        }
    }
}