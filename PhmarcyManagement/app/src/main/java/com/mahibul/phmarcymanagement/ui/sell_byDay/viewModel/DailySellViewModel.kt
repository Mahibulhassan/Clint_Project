package com.mahibul.phmarcymanagement.ui.sell_byDay.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySell
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySellModel

class DailySellViewModel(private val model : DailySellModel): ViewModel() {

    val dailysellListLiveData = MutableLiveData<MutableList<DailySell>>()
    val dailySellListFailourLiveData = MutableLiveData<String>()
    val dailySellDeletionSuccessLiveData = MutableLiveData<Int>()
    val dailySellDeletionFailedLiveData = MutableLiveData<String>()
    val priceCalculateSuccess = MutableLiveData<Int>()
    val tabbleAllDataDelationSuccess = MutableLiveData<Int>()
    val tabbleAllDataDelationFailor = MutableLiveData<String>()

    fun getSellList(){
        model.getDailySellList(object : DataFetchCallback<MutableList<DailySell>> {
            override fun onSuccess(data: MutableList<DailySell>) {
                calculateTotalPrice(data)
                dailysellListLiveData.postValue(data)
            }
            override fun onError(throwable: Throwable) {
                dailySellListFailourLiveData.postValue(throwable.localizedMessage)
            }
        })
    }
     private fun calculateTotalPrice(data: MutableList<DailySell>) {
        var price = 0

            for(i in 0..data.size-1) {
                price = price + data[i].price
            }
         priceCalculateSuccess.postValue(price)
    }

    fun deleteItem(id : Long){
        model.deleteItem(id,object : DataFetchCallback<Int> {
            override fun onSuccess(data: Int) {
                dailySellDeletionSuccessLiveData.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                dailySellDeletionFailedLiveData.postValue(throwable.localizedMessage)
            }
        })
    }

    fun deleteAllItem (){
        model.deleteAllItem(object : DataFetchCallback<Int>{
            override fun onSuccess(data: Int) {
                tabbleAllDataDelationSuccess.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                tabbleAllDataDelationFailor.postValue(throwable.localizedMessage)
            }
        })
    }
}