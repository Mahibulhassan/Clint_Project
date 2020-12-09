package com.mahibul.phmarcymanagement.ui.byelist.addmedicine.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.model.buy_medicine.BuyMedicine
import com.mahibul.phmarcymanagement.data.model.buy_medicine.BuyModel

class AddMedicineViewModel(private val model : BuyModel):ViewModel() {

    val MedicineCreateLiveData = MutableLiveData<BuyMedicine>()
    val MedicineCreateFailedLiveData = MutableLiveData<String>()

    fun addMedicine(medicine : BuyMedicine){
        model.insertMedicine(medicine,object : DataFetchCallback<BuyMedicine>{
            override fun onSuccess(data: BuyMedicine) {
                MedicineCreateLiveData.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                MedicineCreateFailedLiveData.postValue(throwable.localizedMessage)
            }

        })
    }

}