package com.mahibul.phmarcymanagement.ui.sell_byDay.addselldata.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySellModel

class AddSellViewFactory( val model : DailySellModel): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddSellViewModel::class.java)) {
            return AddSellViewModel(model) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class name")
    }
}