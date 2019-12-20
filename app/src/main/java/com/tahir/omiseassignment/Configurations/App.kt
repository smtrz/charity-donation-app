package com.tahir.omiseassignment.Components;

import android.app.Application
import com.tahir.omiseassignment.Modules.ContextModule
import com.tahir.omiseassignment.Modules.NetModule
import com.tahir.omiseassignment.Modules.RepositoryModule


class App : Application() {
    lateinit var appLevelComponent: AppLevelComponent


    override fun onCreate() {
        super.onCreate()
        app = this
        // we only have to set constructor modules or context modules.
        appLevelComponent = DaggerAppLevelComponent.builder()
            .contextModule(ContextModule(this))
            .netModule(NetModule())
            .repositoryModule(RepositoryModule())
            .build()


    }

    fun getApp(): App? {
        return app
    }
    companion object {
        lateinit var app: App
    }


}
