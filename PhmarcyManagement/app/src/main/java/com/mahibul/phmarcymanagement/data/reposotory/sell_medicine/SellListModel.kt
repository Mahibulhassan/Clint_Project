package com.mahibul.phmarcymanagement.data.reposotory.sell_medicine

import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySell

interface SellListModel {
    fun insertTodaySell(dailySell : DailySell,callback: DataFetchCallback<DailySell>)
    fun getMedicineList(callback : DataFetchCallback<MutableList<SellMedicine>>)
    fun getUpdateList(sellMedicine: SellMedicine ,callback: DataFetchCallback<Int>)
}