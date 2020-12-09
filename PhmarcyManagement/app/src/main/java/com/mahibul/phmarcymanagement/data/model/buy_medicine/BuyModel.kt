package com.mahibul.phmarcymanagement.data.model.buy_medicine

import com.mahibul.phmarcymanagement.core.DataFetchCallback

interface BuyModel {
    fun insertMedicine(buyMedicine: BuyMedicine, callback: DataFetchCallback<BuyMedicine>)
    fun getMedicineList(callback: DataFetchCallback<MutableList<BuyMedicine>>)
}