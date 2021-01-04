package com.mahibul.phmarcymanagement.data.SharePreference

import android.content.Context

class AppPreferenceImp(context: Context) : AppPreference {

    private val sharepreferace = context.getSharedPreferences("My_Preference",Context.MODE_PRIVATE)
    private val preference = sharepreferace.edit()

    override fun setName(key: String, value: String) {
        preference.putString(key,value)
        preference.apply()
    }

    override fun getName(key: String): String? {
        return sharepreferace.getString(key,"")
    }

    override fun setPrice(key: String, value: Int) {
        preference.putInt(key,value)
        preference.apply()
    }

    override fun getPrice(key: String): Int? {
        return sharepreferace.getInt(key,0)
    }

    override fun setUnits(key: String, value: Int) {
        preference.putInt(key,value)
        preference.apply()
    }

    override fun getUnits(key: String): Int? {
        return sharepreferace.getInt(key,0)
    }

    override fun setId(key: String, value: Long) {
        preference.putLong(key,value)
        preference.apply()
    }

    override fun getId(key: String): Long? {
        return sharepreferace.getLong(key,-1L)
    }
}