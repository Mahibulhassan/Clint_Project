package com.mahibul.phmarcymanagement.ui.selllist.sellMedicine.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.reposotory.sell_medicine.SellListModel
import com.mahibul.phmarcymanagement.data.reposotory.sell_medicine.SellMedicine

class SellViewModel(private val model : SellListModel): ViewModel() {
    val medicineUpdateLiveData = MutableLiveData<Int>()
    val medicineUpdateFailedLiveData = MutableLiveData<String>()

    fun updateMedicine(sellMedicine: SellMedicine){

        model.getUpdateList(sellMedicine,object : DataFetchCallback<Int> {
            override fun onSuccess(data: Int) {
                medicineUpdateLiveData.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                medicineUpdateFailedLiveData.postValue(throwable.localizedMessage)
            }
        })
    }
}