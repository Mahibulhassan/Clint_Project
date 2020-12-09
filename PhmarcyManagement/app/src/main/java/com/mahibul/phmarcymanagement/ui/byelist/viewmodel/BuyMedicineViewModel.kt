package com.mahibul.phmarcymanagement.ui.byelist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.model.buy_medicine.BuyMedicine
import com.mahibul.phmarcymanagement.data.model.buy_medicine.BuyModel

class BuyMedicineViewModel(private val model : BuyModel): ViewModel() {

    val MedicineListLiveData = MutableLiveData<MutableList<BuyMedicine>>()
    val MedicineListFailourLiveData = MutableLiveData<String>()

    fun getStudentList(){
        model.getMedicineList(object : DataFetchCallback<MutableList<BuyMedicine>>{
            override fun onSuccess(data: MutableList<BuyMedicine>) {
                MedicineListLiveData.postValue(data)
            }
            override fun onError(throwable: Throwable) {
                MedicineListFailourLiveData.postValue(throwable.localizedMessage)
            }

        })
    }
}