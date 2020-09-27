package com.smartherd.controlarduinocar.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.smartherd.controlarduino.storage.Storage

class Preference(ctx: Context) {

    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx)

    private fun getSharedPreferencesValue(storage: Storage): String {
        val (key, default) = storage
        val value = preferences.getString(key, default)!!
        return if (value.isBlank()) default else value
    }

    fun getIpAddress(): String = getSharedPreferencesValue(Storage("ip_address", "192.168.0.133"))
    fun getPort(): String = getSharedPreferencesValue(Storage("port", "4000"))

    fun getMoveForwardValue(): String = getSharedPreferencesValue(Storage("move_forward", "F"))
    fun getMoveBackWardValue(): String = getSharedPreferencesValue(Storage("move_backward", "B"))
    fun getTurnRightValue(): String = getSharedPreferencesValue(Storage("turn_right", "R"))
    fun getTurnLefValue(): String = getSharedPreferencesValue(Storage("turn_left", "L"))
    fun getStopValue() : String = getSharedPreferencesValue(Storage("stop", "S"))

}