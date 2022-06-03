package com.example.baseproject.framework.presentation.features.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {
    val toolbarNameLiveData: MutableLiveData<String> = MutableLiveData()

    fun updateToolbarName(name: String) {
        toolbarNameLiveData.value = name
    }
}