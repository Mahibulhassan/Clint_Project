package com.mahibul.phmarcymanagement.ui.byelist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahibul.phmarcymanagement.data.model.buy_medicine.BuyModel

class BuyMedicineFactory(private val model : BuyModel): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BuyMedicineViewModel::class.java)){
            return BuyMedicineViewModel(model) as T
        }
        throw IllegalArgumentException("Unknown View Model Exception")
    }
}