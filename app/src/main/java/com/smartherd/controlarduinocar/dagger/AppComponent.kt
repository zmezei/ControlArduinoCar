package com.smartherd.controlarduinocar.dagger

import com.smartherd.controlarduinocar.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, PreferenceModule::class, ConnectorModule::class))
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}