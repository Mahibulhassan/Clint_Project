package com.mahibul.phmarcymanagement.data.reposotory.sell_medicine

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.mahibul.phmarcymanagement.constants.*
import com.mahibul.phmarcymanagement.core.DataFetchCallback
import com.mahibul.phmarcymanagement.data.local.DbHelper
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySell
import java.lang.Exception

class SellListModelImp(private val context: Context) : SellListModel {

    override fun insertTodaySell(dailySell: DailySell, callback: DataFetchCallback<DailySell>) {
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

    override fun getMedicineList(callback: DataFetchCallback<MutableList<SellMedicine>>) {
        val dbHelper = DbHelper.getInstance(context)
        val database = dbHelper.readableDatabase

        var cursor: Cursor? = null
        try {
            cursor = database.query(TABLE_BUY_MEDICINE, null, null, null, null, null, null, null)

            if (cursor.moveToFirst() == true) {
                val medicineList = mutableListOf<SellMedicine>()

                do {
                    val name = cursor.getString(cursor.getColumnIndex(COLUMN_medicine_NAME))
                    val price = cursor.getInt(cursor.getColumnIndex(COLUMN_medicine_price))
                    val unite = cursor.getInt(cursor.getColumnIndex(COLUMN_medicine_unit))

                    medicineList.add(SellMedicine(name, price, unite))
                } while (cursor.moveToNext())

                callback.onSuccess(medicineList)
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

    override fun getUpdateList(sellMedicine: SellMedicine, callback: DataFetchCallback<Int>) {
        val dbHelper = DbHelper.getInstance(context)
        val database = dbHelper.writableDatabase

        val contentvalues = ContentValues()
        contentvalues.put(COLUMN_medicine_NAME, sellMedicine.name)
        contentvalues.put(COLUMN_medicine_price, sellMedicine.price)
        contentvalues.put(COLUMN_medicine_unit, sellMedicine.unit)

        try {
            val rowCount = database.update(TABLE_BUY_MEDICINE, contentvalues, "$COLUMN_medicine_NAME = ?",
                    arrayOf(sellMedicine.name))

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
}