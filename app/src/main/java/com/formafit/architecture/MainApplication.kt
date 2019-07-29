package com.formafit.architecture

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.formafit.architecture.core.Core
import com.formafit.architecture.network.NetworkService
import com.formafit.architecture.network.retrofit.IRetrofitService
import com.formafit.architecture.network.retrofit.RetrofitService
import com.formafit.architecture.ui.dev.DevProps
import com.formafit.architecture.ui.repositories.RepositoriesProps
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MainApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {

        bind<Context>() with singleton { this@MainApplication }

        bind<Core>() with singleton { Core(instance()) }

        // Network
        bind<IRetrofitService>() with singleton { RetrofitService("https://api.github.com") }

        // Core services
        bind<NetworkService>() with singleton { NetworkService(instance()) }

        bind<MutableLiveData<RepositoriesProps>>() with provider { instance<Core>().repositoriesProps }
        bind<DevProps>() with provider { instance<Core>().devProps }
    }

}