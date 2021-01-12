package com.mahibul.phmarcymanagement.ui.buylist.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyMedicineData
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyModel

class BuyMedicineViewModel(private val model : BuyModel): ViewModel() {
    val TAG ="medicineviewModel"

    val medicineListLiveData = MutableLiveData<MutableList<BuyMedicineData>>()
    val medicineListFailourLiveData = MutableLiveData<String>()
    val medicineDeletionSuccessLiveData = MutableLiveData<Int>()
    val medicineDeletionFailedLiveData = MutableLiveData<String>()

    fun getMedicineList(){
        model.getMedicineList(object : DataFetchCallback<MutableList<BuyMedicineData>> {
            override fun onSuccess(data: MutableList<BuyMedicineData>) {
                medicineListLiveData.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                medicineListFailourLiveData.postValue(throwable.localizedMessage)
            }

        })
    }

    fun deleteMedicine(id : String){
        model.deleteItem(id, object : DataFetchCallback<Int> {
            override fun onSuccess(data: Int) {
                medicineDeletionSuccessLiveData.postValue(data)
                Log.d(TAG, "onSuccess: $data")
            }

            override fun onError(throwable: Throwable) {
                medicineDeletionFailedLiveData.postValue(throwable.localizedMessage)
            }
        })
    }
}