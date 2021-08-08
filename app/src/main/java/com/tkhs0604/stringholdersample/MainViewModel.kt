package com.tkhs0604.stringholdersample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _textForPlain = MutableLiveData<StringHolder>()
    val textForPlain: LiveData<StringHolder>
        get() = _textForPlain

    private val _textForResource = MutableLiveData<StringHolder>()
    val textForResource: LiveData<StringHolder>
        get() = _textForResource

    init {
        // emit dummy data
        _textForPlain.value = StringHolder.Plain("Hello World!")
        _textForResource.value = StringHolder.Resource(R.string.app_name)
    }
}