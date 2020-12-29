package com.mahibul.phmarcymanagement.ui.DueLIst.updateCustomrdetails.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.Customer
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.DueListModel

class DueEditViewModel(private val model : DueListModel):ViewModel() {
    val CustomerUpdateLiveData = MutableLiveData<Int>()
    val CustomerUpdateFailedLiveData = MutableLiveData<String>()

    fun updateMedicine(dateaCustomer : Customer){
        model.updateDueCustomer(dateaCustomer,object : DataFetchCallback<Int>{
            override fun onSuccess(data: Int) {
                CustomerUpdateLiveData.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                CustomerUpdateFailedLiveData.postValue(throwable.localizedMessage)
            }
        })
    }
}