package com.mahibul.phmarcymanagement.data.reposotory.due_coustomer

import com.mahibul.phmarcymanagement.core.DataFetchCallback

interface DueListModel {
    fun createDueCustomer (customerList : DataCustomer,callback: DataFetchCallback<DataCustomer>)
    fun deleteDueCustomer (id : String ,callback: DataFetchCallback<Int>)
    fun updateDueCustomer (customerList : DataCustomer,callback: DataFetchCallback<Int>)
    fun getCustomerList (callback: DataFetchCallback<MutableList<DataCustomer>>)
}