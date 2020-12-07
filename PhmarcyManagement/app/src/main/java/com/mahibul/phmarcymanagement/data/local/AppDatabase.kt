package com.mahibul.phmarcymanagement.data.local

import androidx.room.Database
import com.mahibul.phmarcymanagement.constants.DATABASE_VERSION
import com.mahibul.phmarcymanagement.data.repository.buyDetails.Medicine

@Database(entities = [Medicine::class], version = DATABASE_VERSION)

class AppDatabase {
}