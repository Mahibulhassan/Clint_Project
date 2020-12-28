package com.mahibul.phmarcymanagement.data.reposotory.due_coustomer

import com.mahibul.phmarcymanagement.core.DataFetchCallback

interface DueListModel {
    fun createDueCustomer (customerList : Customer, callback: DataFetchCallback<Customer>)
    fun deleteDueCustomer (id : String ,callback: DataFetchCallback<Int>)
    fun updateDueCustomer (customerList : Customer, callback: DataFetchCallback<Int>)
    fun getCustomerList (callback: DataFetchCallback<MutableList<Customer>>)
}