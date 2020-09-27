package com.smartherd.controlarduinocar.dagger

import android.content.Context
import com.smartherd.controlarduinocar.connector.Connector
import com.smartherd.controlarduinocar.preferences.Preference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ConnectorModule {

    @Singleton
    @Provides
    internal fun provideConnector(context: Context, preferences: Preference): Connector {
        return Connector(context, preferences)
    }

}