package com.mahibul.phmarcymanagement.ui.buylist.updatemedicine.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyMedicineData
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyModel

class EditMedicineViewModel(private val model : BuyModel): ViewModel()  {
    val MedicineUpdateLiveData = MutableLiveData<Int>()
    val MedicineUpdateFailedLiveData = MutableLiveData<String>()

    fun updateMedicine(medicineData: BuyMedicineData){
        model.getUpdateList(medicineData,object : DataFetchCallback<Int>{
            override fun onSuccess(data: Int) {
                MedicineUpdateLiveData.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                MedicineUpdateFailedLiveData.postValue(throwable.localizedMessage)
            }
        })
    }
}