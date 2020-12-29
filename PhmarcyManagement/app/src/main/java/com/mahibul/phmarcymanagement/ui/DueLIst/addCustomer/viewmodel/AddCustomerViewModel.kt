package com.mahibul.phmarcymanagement.ui.DueLIst.addCustomer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.Customer
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.DueListModel

class AddCustomerViewModel(private val model : DueListModel) : ViewModel() {

    val customerCreateLiveData = MutableLiveData<Customer>()
    val customerCreateFailedLiveData = MutableLiveData<String>()

    fun addCustomer(customer: Customer){
        model.createDueCustomer(customer,object : DataFetchCallback<Customer>{
            override fun onSuccess(data: Customer) {
                customerCreateLiveData.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                customerCreateFailedLiveData.postValue(throwable.localizedMessage)
            }
        })
    }
}