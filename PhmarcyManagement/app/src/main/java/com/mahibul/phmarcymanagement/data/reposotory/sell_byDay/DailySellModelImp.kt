package com.mahibul.phmarcymanagement.data.reposotory.sell_byDay

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.mahibul.phmarcymanagement.constants.COLUMN_medicine_NAME
import com.mahibul.phmarcymanagement.constants.COLUMN_medicine_price
import com.mahibul.phmarcymanagement.constants.Colum_sell_id
import com.mahibul.phmarcymanagement.constants.Table_sell
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.local.DbHelper
import java.lang.Exception

class DailySellModelImp(private val context: Context)  : DailySellModel {
    private var constant =1

    override fun insertDailysell(dailySell: DailySell, callback: DataFetchCallback<DailySell>) {
        val dbHelper = DbHelper.getInstance(context)
        val database = dbHelper.writableDatabase

        val contentvalues = ContentValues()
        contentvalues.put(COLUMN_medicine_NAME,dailySell.name)
        contentvalues.put(COLUMN_medicine_price,dailySell.price)
        try {
            val id = database.insertOrThrow(Table_sell, null, contentvalues)
            if (id > 0) {
                dailySell.id = id
                callback.onSuccess(dailySell)
            } else
                callback.onError(Throwable("Insertion failed for unknown reason"))
        } catch (e: Exception) {
            callback.onError(e)
        } finally {
            database.close()
        }
    }

    override fun getDailySellList(callback: DataFetchCallback<MutableList<DailySell>>) {
        val dbHelper = DbHelper.getInstance(context)
        val database = dbHelper.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database.query(
                    Table_sell,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            )
            val dailysell = mutableListOf<DailySell>()
            if (cursor?.moveToFirst() == true) {


                do {
                    val id = cursor.getInt(0) // the index of _id is 0
                    val name = cursor.getString(cursor.getColumnIndex(COLUMN_medicine_NAME))
                    val price = cursor.getInt(cursor.getColumnIndex(COLUMN_medicine_price))

                    dailysell.add(DailySell(id.toLong(), name, price))

                } while (cursor.moveToNext())

                callback.onSuccess(dailysell)
            }else{
                callback.onSuccess(dailysell)
            }
        } catch (e: Exception) {
            callback.onError(e)
        } finally {
            cursor?.close()
            database?.close()
        }
    }


    override fun getUpdateList(dailySell: DailySell, callback: DataFetchCallback<Int>) {
        val dbHelper = DbHelper.getInstance(context)
        val database = dbHelper.writableDatabase

        val contentvalues = ContentValues()
        contentvalues.put(COLUMN_medicine_NAME,dailySell.name)
        contentvalues.put(COLUMN_medicine_price,dailySell.price)

        try {
            val rowCount = database.update(
                    Table_sell,
                    contentvalues,
                    "$Colum_sell_id = ? ",
                    arrayOf(dailySell.id.toString())
            )

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

    override fun deleteItem(id: Long, callback: DataFetchCallback<Int>) {
        val dbHelper = DbHelper.getInstance(context)
        val database = dbHelper.writableDatabase

        try {
            val deleteRowCount = database.delete(
                    Table_sell,
                    "$Colum_sell_id = ? ",
                    arrayOf(id.toString())
            )

            if (deleteRowCount > 0) {
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

    override fun deleteAllItem(callback: DataFetchCallback<Int>) {
        val dbHelper = DbHelper.getInstance(context)
        val database = dbHelper.writableDatabase
        try {
           database.execSQL("DELETE FROM Today_Sell")
            constant++
            if(constant>100){
                constant=1
            }
            if ( constant> 0) {
                callback.onSuccess(constant)
            } else {
                callback.onError(Throwable("No data is deleted"))
            }
        } catch (e: Exception) {
            callback.onError(e)
        } finally {
            database.close()
        }
    }
}