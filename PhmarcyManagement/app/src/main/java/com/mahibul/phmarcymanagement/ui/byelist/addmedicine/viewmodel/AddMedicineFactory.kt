package com.mahibul.phmarcymanagement.ui.byelist.addmedicine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyModel

class AddMedicineFactory( val model : BuyModel):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddMedicineViewModel::class.java)){
            return AddMedicineViewModel(model) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class name")
    }
}