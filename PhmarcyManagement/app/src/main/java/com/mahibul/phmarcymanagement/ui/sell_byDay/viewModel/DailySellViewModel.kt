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

    fun getSellList(){
        model.getDailySellList(object : DataFetchCallback<MutableList<DailySell>> {
            override fun onSuccess(data: MutableList<DailySell>) {
                dailysellListLiveData.postValue(data)
            }
            override fun onError(throwable: Throwable) {
                dailySellListFailourLiveData.postValue(throwable.localizedMessage)
            }

        })
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
}