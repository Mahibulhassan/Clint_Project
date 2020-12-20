package com.mahibul.phmarcymanagement.data.reposotory.buy_medicine

import com.mahibul.phmarcymanagement.core.DataFetchCallback

interface BuyModel {
    fun insertMedicine(buyMedicineData: BuyMedicineData, callback: DataFetchCallback<BuyMedicineData>)
    fun getMedicineList(callback: DataFetchCallback<MutableList<BuyMedicineData>>)
    fun getUpdateList(buyMedicineData: BuyMedicineData,callback: DataFetchCallback<Int>)
    fun deleteItem(id : String ,callback: DataFetchCallback<Int>)
}