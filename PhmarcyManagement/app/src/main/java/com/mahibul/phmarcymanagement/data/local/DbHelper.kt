package com.mahibul.phmarcymanagement.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mahibul.phmarcymanagement.constants.*

object DbHelper {

    private lateinit var context: Context

    private val dbHelper by lazy {
        object : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
            override fun onCreate(db: SQLiteDatabase?) {
                /**
                 * Here, we execute this SQL query:
                 * CREATE TABLE student(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, registration_no INTEGER NOT NULL UNIQUE, phone TEXT, email TEXT )
                 */
                val s = "CREATE TABLE $TABLE_BUY_MEDICINE($COLUMN_medicine_NAME TEXT NOT NULL PRIMARY KEY UNIQUE, $COLUMN_medicine_price INTEGER NOT NULL, $COLUMN_medicine_unit INTEGER NOT NULL)"
                db?.execSQL(s)
                val due = "CREATE TABLE $TABLE_DUE_Customer($Column_customer_name TEXT NOT NULL PRIMARY KEY UNIQUE, $Column_customer_price INTEGER NOT NULL)"
                db?.execSQL(due)
                val sell_day ="CREATE TABLE $Table_sell($Colum_sell_id INTEGER PRIMARY KEY AUTOINCREMENT,$COLUMN_medicine_NAME TEXT NOT NULL, $COLUMN_medicine_price INTEGER NOT NULL)"
                db?.execSQL(sell_day)
                /*     val CREATE_STUDENT_TABLE = ("CREATE TABLE " + TABLE_STUDENT + "("
                       + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                       + COLUMN_STUDENT_NAME + " TEXT NOT NULL, "
                       + COLUMN_STUDENT_REGISTRATION + " INTEGER NOT NULL UNIQUE, "
                       + COLUMN_STUDENT_PHONE + " TEXT, " //nullable
                       + COLUMN_STUDENT_EMAIL + " TEXT " + ")")
               db?.execSQL(CREATE_STUDENT_TABLE)*/
            }
            override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
                // Drop older table if existed
                db?.execSQL("DROP TABLE IF EXISTS $TABLE_BUY_MEDICINE")
                db?.execSQL("DROP TABLE IF EXISTS $TABLE_DUE_Customer")
                db?.execSQL("DROP TABLE IF EXISTS $Table_sell")
                // Create tables again
                onCreate(db)
            }

        }
    }
    fun getInstance(context: Context): SQLiteOpenHelper {
        DbHelper.context = context
        return dbHelper
    }
}