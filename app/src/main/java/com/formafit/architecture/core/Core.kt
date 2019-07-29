package com.formafit.architecture.core

import androidx.lifecycle.MutableLiveData
import com.formafit.architecture.Command
import com.formafit.architecture.network.models.ErrorModel
import com.formafit.architecture.network.models.RepositoriesResponseModel
import com.formafit.architecture.ui.dev.DevProps
import com.formafit.architecture.ui.repositories.RepositoriesProps

interface NetworkOperations {

    sealed class Result<T> {
        class Data<T>(val value: T) : Result<T>()
        class NoInternetConnection<T> : Result<T>()
        class NotFound<T>(val errorModel: ErrorModel) : Result<T>()
        class ServerUnavailable<T> : Result<T>()
        class Unauthorized<T>(val errorModel: ErrorModel) : Result<T>()
        class UndefinedError<T>(val errorModel: ErrorModel) : Result<T>()

        fun <R> mapTo(map: (T) -> R): Result<R> = when (this) {
            is Data<T> -> Data(map(this.value))
            is NoInternetConnection<T> -> NoInternetConnection()
            is ServerUnavailable<T> -> ServerUnavailable()
            is Unauthorized<T> -> Unauthorized(this.errorModel)
            is UndefinedError<T> -> UndefinedError(this.errorModel)
            is NotFound<T> -> NotFound(this.errorModel)
        }
    }

    suspend fun getAllRepositories(): Result<List<RepositoriesResponseModel>>
}

interface RoutingOperations {
    fun popCurrentScreen()
    fun openRepositories()
    fun openPlaybook()
}

interface ActivityOperations {
    var disableBackButton: Boolean
    fun showToast(text: String)
}

class Core(internal val networkOperations: NetworkOperations) {

    lateinit var activityOperations: ActivityOperations
    lateinit var routingOperations: RoutingOperations

    fun registerActivity(activityOperations: ActivityOperations) {
        this.activityOperations = activityOperations
    }

    fun registerRouting(routingOperations: RoutingOperations) {
        this.routingOperations = routingOperations
    }

    val repositoriesPresenter = RepositoriesPresenter(this)

    val repositoriesProps = MutableLiveData<RepositoriesProps>()

    val devProps = DevProps(Command(repositoriesPresenter::openRepositories), Command(::openPlaybook))

    fun openPlaybook() {
        routingOperations.openPlaybook()
    }

    fun <T> handleErrors(result: NetworkOperations.Result<T>?) {
        if (result != null) {
            when (result) {
                is NetworkOperations.Result.NoInternetConnection -> {
                    activityOperations.showToast("Unfortunately, no internet connection available")
                }
                is NetworkOperations.Result.UndefinedError -> {
                    activityOperations.showToast(result.errorModel.message)
                }
                is NetworkOperations.Result.Unauthorized -> {
                    activityOperations.showToast(result.errorModel.message)
                }
                is NetworkOperations.Result.NotFound -> {
                    activityOperations.showToast(result.errorModel.message)
                }
                is NetworkOperations.Result.ServerUnavailable -> {
                    activityOperations.showToast("Sorry, server is unavailable at the moment")
                }
                else -> error("Didn't expect such type of the network result: $result")
            }
        }
    }

    suspend fun <T> handleDataAndErrors(result: NetworkOperations.Result<T>, dataHandler: suspend (T) -> Unit) {
        when (result) {
            is NetworkOperations.Result.Data -> {
                dataHandler(result.value)
            }
            else -> handleErrors(result)
        }
    }
}