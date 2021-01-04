package com.mahibul.phmarcymanagement.data.SharePreference

interface AppPreference {

    companion object{
        const val Name = "name"
        const val Units =  "Units"
        const val price ="Price"
        const val id ="id"
    }
    fun setName(key: String ,value:String)
    fun getName(key:String):String?
    fun setPrice(key: String,value:Int)
    fun getPrice(key: String):Int?
    fun setUnits(key:String,value:Int)
    fun getUnits(key:String): Int?
    fun setId(key:String,value : Long)
    fun getId(key:String):Long?
}