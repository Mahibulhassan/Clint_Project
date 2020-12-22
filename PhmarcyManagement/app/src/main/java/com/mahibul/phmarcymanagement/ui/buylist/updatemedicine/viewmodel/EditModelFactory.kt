package com.mahibul.phmarcymanagement.ui.buylist.updatemedicine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyModel
import com.mahibul.phmarcymanagement.ui.buylist.addmedicine.viewmodel.AddMedicineViewModel

class EditModelFactory(val model : BuyModel) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EditMedicineViewModel::class.java)){
            return EditMedicineViewModel(model) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class name")
    }
}