package com.smartherd.controlarduinocar.app

import android.app.Application
import com.smartherd.controlarduinocar.dagger.AppComponent
import com.smartherd.controlarduinocar.dagger.AppModule
import com.smartherd.controlarduinocar.dagger.DaggerAppComponent

class CarControlApplication: Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    protected fun initDagger(application: CarControlApplication): AppComponent {
        return DaggerAppComponent.builder().appModule(AppModule(application)).build()
    }

    fun getAppComponent(): AppComponent? {
        return this.appComponent
    }

}