package com.example.muslimway.utils

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val msg:String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    object Idle : DataState<Nothing>()
}
