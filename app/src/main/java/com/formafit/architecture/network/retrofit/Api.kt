package com.formafit.architecture.network.retrofit

import com.formafit.architecture.network.models.RepositoriesResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    companion object {
        private const val REPOSITORIES = "/repositories"
    }

    @GET(REPOSITORIES)
    fun repositories(): Deferred<Response<List<RepositoriesResponseModel>>>
}
