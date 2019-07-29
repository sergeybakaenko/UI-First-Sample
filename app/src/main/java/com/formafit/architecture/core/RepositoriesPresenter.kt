package com.formafit.architecture.core

import com.formafit.architecture.network.models.RepositoriesResponseModel
import com.formafit.architecture.nop
import com.formafit.architecture.ui.repositories.RepositoriesProps

class RepositoriesPresenter(private val core: Core) {

    class RepositoriesState(var list: List<RepositoriesResponseModel>? = null)

    val repositoriesState = RepositoriesState()

    fun present() {
        core.repositoriesProps.value = RepositoriesProps(present(repositoriesState))
    }

    private fun present(state: RepositoriesState): RepositoriesProps.Repositories {
        return state.list.let {
            when {
                it == null -> RepositoriesProps.Repositories.Loading
                it.isEmpty() -> RepositoriesProps.Repositories.Empty
                it.isNotEmpty() -> RepositoriesProps.Repositories.Loaded(it.map { model ->
                    RepositoriesProps.RepositoryProps(nop(), model.name, model.owner.avatarUrl)
                })
                else -> error("Didn't expect such state of the repositories list")
            }
        }
    }

    suspend fun openRepositories() {
        repositoriesState.list = null
        present()
        routeToRepositories()

        when (val networkResult = core.networkOperations.getAllRepositories()) {
            is NetworkOperations.Result.Data -> {
                repositoriesState.list = networkResult.value
                present()
            }
            else -> {
                repositoriesState.list = arrayListOf()
                present()
                core.handleErrors(networkResult)
            }
        }
    }

    private fun routeToRepositories() {
        core.routingOperations.openRepositories()
    }
}