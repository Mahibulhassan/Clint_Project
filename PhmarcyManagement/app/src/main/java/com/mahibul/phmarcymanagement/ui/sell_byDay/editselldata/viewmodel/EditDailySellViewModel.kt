package com.mahibul.phmarcymanagement.ui.sell_byDay.editselldata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySell
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySellModel

class EditDailySellViewModel(private val model : DailySellModel): ViewModel() {
    val dailySellUpdateLiveData = MutableLiveData<Int>()
    val dailySellUpdateFailedLiveData = MutableLiveData<String>()

    fun updateDailySell(dailysell : DailySell){
        model.getUpdateList(dailysell,object :DataFetchCallback<Int>{
            override fun onSuccess(data: Int) {
                dailySellUpdateLiveData.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                dailySellUpdateFailedLiveData.postValue(throwable.localizedMessage)
            }
        })
    }
}