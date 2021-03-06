package com.mahibul.phmarcymanagement.ui.buylist.addmedicine.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyMedicineData
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyModel

class AddMedicineViewModel(private val model : BuyModel):ViewModel() {

    val medicineCreateLiveData = MutableLiveData<BuyMedicineData>()
    val medicineCreateFailedLiveData = MutableLiveData<String>()

    fun addMedicine(medicineData : BuyMedicineData){
        model.insertMedicine(medicineData,object : DataFetchCallback<BuyMedicineData>{
            override fun onSuccess(data: BuyMedicineData) {
                medicineCreateLiveData.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                medicineCreateFailedLiveData.postValue(throwable.localizedMessage)
            }
        })
    }
}