package com.mahibul.phmarcymanagement.ui.DueLIst.addCustomer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.DataCustomer
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.DueListModel

class AddCustomerViewModel(private val model : DueListModel) : ViewModel() {

    val customerCreateLiveData = MutableLiveData<DataCustomer>()
    val customerCreateFailedLiveData = MutableLiveData<String>()

    fun addCustomer(dataCustomer: DataCustomer){
        model.createDueCustomer(dataCustomer,object : DataFetchCallback<DataCustomer>{
            override fun onSuccess(data: DataCustomer) {
                customerCreateLiveData.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                customerCreateFailedLiveData.postValue(throwable.localizedMessage)
            }
        })
    }
}