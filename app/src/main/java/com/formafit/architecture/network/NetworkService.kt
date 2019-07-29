package com.formafit.architecture.network

import com.formafit.architecture.core.NetworkOperations
import com.formafit.architecture.network.models.ErrorModel
import com.formafit.architecture.network.models.RepositoriesResponseModel
import com.formafit.architecture.network.retrofit.IRetrofitService
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import retrofit2.Response
import java.net.UnknownHostException

class NetworkService(retrofitService: IRetrofitService) : NetworkOperations {

    private val api = retrofitService.api

    override suspend fun getAllRepositories(): NetworkOperations.Result<List<RepositoriesResponseModel>> =
        api.repositories().performApiCall()
}

suspend fun <T> Deferred<Response<T>>.performApiCall(): NetworkOperations.Result<T> {
    return try {
        val result = this.await()
        val error = Gson().fromJson(result.errorBody()?.string(), ErrorModel::class.java)
        return when {
            result.isSuccessful -> NetworkOperations.Result.Data(result.body()!!)
            result.code() == 401 -> NetworkOperations.Result.UndefinedError(error)
            result.code() == 404 -> NetworkOperations.Result.NotFound(error)
            result.code() == 500 -> NetworkOperations.Result.ServerUnavailable()
            else -> NetworkOperations.Result.UndefinedError(error)
        }
    } catch (unknownHostException: UnknownHostException) {
        NetworkOperations.Result.NoInternetConnection()
    }
}