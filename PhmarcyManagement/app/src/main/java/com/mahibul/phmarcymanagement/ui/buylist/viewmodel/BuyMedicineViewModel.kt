package com.mahibul.phmarcymanagement.ui.buylist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyMedicineData
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyModel

class BuyMedicineViewModel(private val model : BuyModel): ViewModel() {

    val MedicineListLiveData = MutableLiveData<MutableList<BuyMedicineData>>()
    val MedicineListFailourLiveData = MutableLiveData<String>()
    val MedicineDeletionSuccessLiveData = MutableLiveData<Int>()
    val MedicineDeletionFailedLiveData = MutableLiveData<String>()

    fun getMedicineList(){
        model.getMedicineList(object : DataFetchCallback<MutableList<BuyMedicineData>>{
            override fun onSuccess(data: MutableList<BuyMedicineData>) {
                MedicineListLiveData.postValue(data)
            }
            override fun onError(throwable: Throwable) {
                MedicineListFailourLiveData.postValue(throwable.localizedMessage)
            }

        })
    }

    fun deleteMedicine(id : String){
        model.deleteItem(id,object : DataFetchCallback<Int>{
            override fun onSuccess(data: Int) {
                MedicineDeletionSuccessLiveData.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                MedicineDeletionFailedLiveData.postValue(throwable.localizedMessage)
            }
        })
    }
}