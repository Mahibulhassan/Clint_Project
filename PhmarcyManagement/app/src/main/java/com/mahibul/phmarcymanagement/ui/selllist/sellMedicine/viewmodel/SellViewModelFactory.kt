package com.mahibul.phmarcymanagement.ui.selllist.sellMedicine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahibul.phmarcymanagement.data.reposotory.sell_medicine.SellListModel

class SellViewModelFactory (val model : SellListModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SellViewModel::class.java)){
            return SellViewModel(model) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class name")
    }
}