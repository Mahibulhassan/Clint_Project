package com.mahibul.phmarcymanagement.ui.DueLIst.addCustomer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.DueListModel

class AddCustomerFactory(private val model : DueListModel): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddCustomerViewModel::class.java)){
            return AddCustomerViewModel(model) as T
        }
        throw IllegalArgumentException("Unknown View Model Exception")
    }

}