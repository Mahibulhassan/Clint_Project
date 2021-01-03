package com.mahibul.phmarcymanagement.ui.sell_byDay.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySellModel

class DailySellViewFactory (private val model : DailySellModel): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DailySellViewModel::class.java)){
            return DailySellViewModel(model) as T
        }
        throw IllegalArgumentException("Unknown View Model Exception")
    }
}