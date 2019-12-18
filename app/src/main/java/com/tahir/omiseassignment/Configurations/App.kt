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
            .netModule(NetModule("https://virtserver.swaggerhub.com/chakritw/tamboon-api/1.0.0/"))
            .repositoryModule(RepositoryModule())
            .build()


    }

    companion object {
        lateinit var app: App
    }


}
