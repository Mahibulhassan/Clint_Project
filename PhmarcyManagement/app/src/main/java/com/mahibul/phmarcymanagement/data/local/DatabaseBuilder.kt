package com.mahibul.phmarcymanagement.data.local

import android.content.Context
import androidx.room.Room
import com.mahibul.phmarcymanagement.constants.DATABASE_NAME

object DatabaseBuilder {

    fun getInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}