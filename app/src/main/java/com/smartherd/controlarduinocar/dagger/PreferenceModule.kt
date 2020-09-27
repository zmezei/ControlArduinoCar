package com.smartherd.controlarduinocar.dagger

import android.content.Context
import com.smartherd.controlarduinocar.preferences.Preference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Singleton
    @Provides
    internal fun providePreference(context: Context): Preference {
        return Preference(context)
    }
}