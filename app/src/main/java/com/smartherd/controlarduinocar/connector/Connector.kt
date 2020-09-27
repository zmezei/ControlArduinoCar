package com.smartherd.controlarduinocar.connector

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.smartherd.controlarduinocar.preferences.Preference
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private interface ControlApiService {

    @GET("/move")
    fun move(@Query("dir") direction: String): Call<String>

}

class Connector(context: Context, preferences: Preference) {
    private var preferences = preferences
    private var service: ControlApiService? = null

    init {
        val url = "http://${preferences.getIpAddress()}:${preferences.getPort()}"
        try {
            val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(ScalarsConverterFactory.create()).build()
            service = retrofit.create(ControlApiService::class.java)
        } catch (e: IllegalArgumentException) {
            Toast.makeText(
                context,
                "Invalid hostname",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    fun moveForward() = sendMoveRequest(preferences.getMoveForwardValue())
    fun moveBackward() = sendMoveRequest(preferences.getMoveBackWardValue())
    fun turnRight() = sendMoveRequest(preferences.getTurnRightValue())
    fun turnLeft() = sendMoveRequest(preferences.getTurnLefValue())
    fun stopMoving() = sendMoveRequest(preferences.getStopValue())

    private fun sendMoveRequest(dir: String) {
        if (service == null) return
        val request = (service as ControlApiService).move(dir)
        request.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("Response", response.body().toString())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("Failure", t.message.toString())
            }
        })
    }
}