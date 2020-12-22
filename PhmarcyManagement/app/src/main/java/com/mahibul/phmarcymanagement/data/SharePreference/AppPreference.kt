package com.mahibul.phmarcymanagement.data.SharePreference

interface AppPreference {

    companion object{
        const val Name = "name"
        const val Units =  "Units"
    }
    fun setName(key: String ,value:String)
    fun getName(key:String):String?
    fun setUnits(key:String,value:Int)
    fun getUnits(key:String): Int?
}