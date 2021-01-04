package com.mahibul.phmarcymanagement.ui.sell_byDay.addselldata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySell
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySellModel

class AddSellViewModel(private val model : DailySellModel): ViewModel()  {
    val dailysellCreateLiveData = MutableLiveData<DailySell>()
    val dailysellCreateFailedLiveData = MutableLiveData<String>()

    fun addSellItem(selldata : DailySell){
        model.insertDailysell(selldata,object : DataFetchCallback<DailySell>{
            override fun onSuccess(data: DailySell) {
                dailysellCreateLiveData.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                dailysellCreateFailedLiveData.postValue(throwable.localizedMessage)
            }
        })
    }
}