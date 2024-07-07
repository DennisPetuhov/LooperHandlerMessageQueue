package com.example.looperhandlermessagequeue

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel : ViewModel() {
    private val _msgFlow = MutableStateFlow("")
    val msgFlow: StateFlow<String> get() = _msgFlow

    fun setMsg(msg: String) {
        println("**** $msg ****")
        _msgFlow.value = msg
    }
}