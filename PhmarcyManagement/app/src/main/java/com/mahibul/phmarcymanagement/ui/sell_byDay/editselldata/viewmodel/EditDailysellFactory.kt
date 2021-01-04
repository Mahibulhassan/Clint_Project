package com.mahibul.phmarcymanagement.ui.sell_byDay.editselldata.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySellModel

class EditDailysellFactory(val model : DailySellModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditDailySellViewModel::class.java)) {
            return EditDailySellViewModel(model) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class name")
    }
}