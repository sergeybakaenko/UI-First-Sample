package com.formafit.architecture.ui.repositories

import com.formafit.architecture.Command

class RepositoriesProps(val repositories: Repositories = Repositories.Empty) {

    data class RepositoryProps(
        val openDetails: Command,
        val name: String,
        val image: String?
    )

    sealed class Repositories {
        class Loaded(val items: List<RepositoryProps>) : Repositories()
        object Loading : Repositories()
        object Empty : Repositories()

    }
}