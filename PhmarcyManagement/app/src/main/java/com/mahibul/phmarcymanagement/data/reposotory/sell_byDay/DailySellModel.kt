package com.mahibul.phmarcymanagement.data.reposotory.sell_byDay

import com.mahibul.phmarcymanagement.core.DataFetchCallback

interface DailySellModel {
    fun insertDailysell(dailySell: DailySell, callback: DataFetchCallback<DailySell>)
    fun getDailySellList(callback: DataFetchCallback<MutableList<DailySell>>)
    fun getUpdateList(dailySell: DailySell, callback: DataFetchCallback<Int>)
    fun deleteItem(id : Long ,callback: DataFetchCallback<Int>)
}