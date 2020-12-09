package com.mahibul.phmarcymanagement.core

interface DataFetchCallback<T> {
    fun onSuccess(data : T)
    fun onError(throwable: Throwable)
}