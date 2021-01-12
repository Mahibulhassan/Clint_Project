package com.mahibul.phmarcymanagement.data.reposotory.due_coustomer

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.mahibul.phmarcymanagement.constants.*
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.local.DbHelper
import java.lang.Exception

class DueListModelImp(private val context: Context) : DueListModel {

    override fun createDueCustomer(customerList: Customer, callback: DataFetchCallback<Customer>) {
        val dbHelper = DbHelper.getInstance(context)
        val database = dbHelper.writableDatabase

        val contentvalues = ContentValues()
        contentvalues.put(Column_customer_name,customerList.customer_name)
        contentvalues.put(Column_customer_price,customerList.customer_due)
        try {
            val id = database.insertOrThrow(TABLE_DUE_Customer, null, contentvalues)
            if (id > 0) {
                callback.onSuccess(customerList)
            } else
                callback.onError(Throwable("Insertion failed for unknown reason"))
        } catch (e: Exception) {
            callback.onError(e)
        } finally {
            database.close()
        }
    }

    override fun deleteDueCustomer(id: String, callback: DataFetchCallback<Int>) {
        val dbHelper = DbHelper.getInstance(context)
        val database = dbHelper.writableDatabase

        try {
            val deleteRowCount = database.delete(
                    TABLE_DUE_Customer,
                    "$Column_customer_name = ? ",
                    arrayOf(id)
            )

            if (deleteRowCount >= 0) {
                callback.onSuccess(deleteRowCount)
            } else {
                callback.onError(Throwable("No data is deleted"))
            }
        } catch (e: Exception) {
            callback.onError(e)
        } finally {
            database.close()
        }
    }


    override fun updateDueCustomer(customerList: Customer, callback: DataFetchCallback<Int>) {
        val dbHelper = DbHelper.getInstance(context)
        val database = dbHelper.writableDatabase

        val contentvalues = ContentValues()
        contentvalues.put(Column_customer_name, customerList.customer_name)
        contentvalues.put(Column_customer_price, customerList.customer_due)

        try {
            val rowCount = database.update(TABLE_DUE_Customer, contentvalues, "$Column_customer_name = ?",
                    arrayOf(customerList.customer_name))

            if (rowCount > 0) {
                callback.onSuccess(rowCount)
            } else {
                callback.onError(Throwable("No item is updated"))
            }

        } catch (e: Exception) {
            callback.onError(e)
        } finally {
            database.close()
        }
    }

    override fun getCustomerList(callback: DataFetchCallback<MutableList<Customer>>) {
        val dbHelper = DbHelper.getInstance(context)
        val database = dbHelper.readableDatabase

        var cursor: Cursor? = null
        try {
            cursor = database.query(TABLE_DUE_Customer, null, null, null, null, null, null, null)
            val customerList = mutableListOf<Customer>()
            if (cursor.moveToFirst() == true) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex(Column_customer_name))
                    val price = cursor.getInt(cursor.getColumnIndex(Column_customer_price))
                    customerList.add(Customer(name, price))
                } while (cursor.moveToNext())
                callback.onSuccess(customerList)
            }else{
                callback.onSuccess(customerList)
            }
        } catch (e: Exception) {
            callback.onError(e)
        } finally {
            if (cursor != null) {
                cursor.close()
            }
            database.close()
        }
    }
}