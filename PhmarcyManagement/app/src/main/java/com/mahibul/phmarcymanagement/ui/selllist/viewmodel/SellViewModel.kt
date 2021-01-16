package com.mahibul.phmarcymanagement.ui.selllist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.reposotory.sell_medicine.SellListModel
import com.mahibul.phmarcymanagement.data.reposotory.sell_medicine.SellMedicine

class SellViewModel(private val model : SellListModel): ViewModel() {
    val medicineListLiveData = MutableLiveData<MutableList<SellMedicine>>()
    val medicineListFailourLiveData = MutableLiveData<String>()
    fun getMedicineList(){
        model.getMedicineList(object : DataFetchCallback<MutableList<SellMedicine>> {
            override fun onSuccess(data: MutableList<SellMedicine>) {
                val sortmedicine = data.sortedBy { data->data.name }
                medicineListLiveData.postValue(sortmedicine as MutableList<SellMedicine>?)
            }
            override fun onError(throwable: Throwable) {
                medicineListFailourLiveData.postValue(throwable.localizedMessage)
            }
        })
    }
}