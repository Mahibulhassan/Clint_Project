package com.mahibul.phmarcymanagement.data.repository.buyDetails

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mahibul.phmarcymanagement.constants.*

@Entity(indices = [Index(value = [COLUMN_REGISTRATION], unique = true)])
class Medicine {
    @PrimaryKey(autoGenerate = true) var id: Long=0 , // have to set 0 for auto generate ID
    @ColumnInfo(name = COLUMN_medicine_NAME) var name: String,
    @ColumnInfo(name = COLUMN_medicine_price) var price : Int,
    @ColumnInfo(name = COLUMN_medicine_unit) var unit : Int
}