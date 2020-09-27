package com.smartherd.controlarduinocar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import com.smartherd.controlarduinocar.app.CarControlApplication
import com.smartherd.controlarduinocar.connector.Connector

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connector: Connector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        (this.application as CarControlApplication).getAppComponent()!!.inject(this)

        btnForward.setOnTouchListener { v:View, e:MotionEvent -> onButtonTouch(v,e) }
        btnBackward.setOnTouchListener{ v:View, e:MotionEvent -> onButtonTouch(v, e) }
        btnRight.setOnTouchListener{ v:View, e:MotionEvent -> onButtonTouch(v, e) }
        btnLeft.setOnTouchListener{ v:View, e:MotionEvent -> onButtonTouch(v, e) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onButtonTouch(v: View, event: MotionEvent): Boolean {
        val isTouchDown = event.action == MotionEvent.ACTION_DOWN
        val isTouchUp = event.action == MotionEvent.ACTION_UP
        if (isTouchDown) {
            when (v.id) {
                R.id.btnForward -> {
                    connector.moveForward()
                }
                R.id.btnBackward -> {
                    connector.moveBackward()
                }
                R.id.btnRight -> {
                    connector.turnRight()
                }
                R.id.btnLeft -> {
                    connector.turnLeft()
                }
            }
            return true
        }
        if (isTouchUp) {
            connector.stopMoving()
            return true
        }
        return false
    }
}
