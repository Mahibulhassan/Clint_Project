package com.mahibul.phmarcymanagement.data.reposotory.sell_medicine

import com.mahibul.phmarcymanagement.core.DataFetchCallback

interface SellListModel {

    fun getMedicineList(callback : DataFetchCallback<MutableList<SellMedicine>>)
    fun getUpdateList(sellMedicine: SellMedicine ,callback: DataFetchCallback<Int>)
}