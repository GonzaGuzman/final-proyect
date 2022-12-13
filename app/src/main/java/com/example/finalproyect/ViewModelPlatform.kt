package com.example.finalproyect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class ViewModelPlatform(val repository: PlatformRepository) : ViewModel() {
    private val _statusSite = MutableLiveData<Boolean>(false)
    val statusSite: LiveData<Boolean> = _statusSite

    private val _nameSite = MutableLiveData<String>()
    val nameSite: LiveData<String> = _nameSite

    private val _dirSite = MutableLiveData<String>()
    val dirSite: LiveData<String> = _dirSite

    fun setName(name: String) {
        _nameSite.postValue(name)
    }

    fun setDir(dirSite: String) {
        _dirSite.postValue(dirSite)
    }

    fun validateSite() {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.IO) {
            _statusSite.postValue(repository.getStatus(dirSite.value.toString()))
        }
    }
}