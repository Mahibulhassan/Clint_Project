package com.mahibul.phmarcymanagement.data.local

interface DataChangeLIstner {
    fun onDataChanged()
    fun onDataSetChangeError(error: String)
}