package com.formafit.architecture.playbook.scene

import androidx.lifecycle.MutableLiveData
import com.formafit.architecture.nop
import com.formafit.architecture.ui.repositories.RepositoriesProps

class RepositoriesScene {

    var props = MutableLiveData<RepositoriesProps>()

    fun showEmpty() {
        props.value = RepositoriesProps(RepositoriesProps.Repositories.Empty)
    }

    fun showLoading() {
        props.value = RepositoriesProps(RepositoriesProps.Repositories.Loading)
    }

    fun showLoaded() {
        props.value = RepositoriesProps(
            RepositoriesProps.Repositories.Loaded(
                listOf(
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 1", "https://api.adorable.io/avatars/150/some@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 2", "https://api.adorable.io/avatars/150/some1@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 3", "https://api.adorable.io/avatars/150/some2@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 4", "https://api.adorable.io/avatars/150/some3@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 5", "https://api.adorable.io/avatars/150/some4@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 6", "https://api.adorable.io/avatars/150/some5@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 1", "https://api.adorable.io/avatars/150/some@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 2", "https://api.adorable.io/avatars/150/some1@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 3", "https://api.adorable.io/avatars/150/some2@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 4", "https://api.adorable.io/avatars/150/some3@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 5", "https://api.adorable.io/avatars/150/some4@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 6", "https://api.adorable.io/avatars/150/some5@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 1", "https://api.adorable.io/avatars/150/some@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 2", "https://api.adorable.io/avatars/150/some1@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 3", "https://api.adorable.io/avatars/150/some2@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 4", "https://api.adorable.io/avatars/150/some3@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 5", "https://api.adorable.io/avatars/150/some4@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 6", "https://api.adorable.io/avatars/150/some5@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 1", "https://api.adorable.io/avatars/150/some@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 2", "https://api.adorable.io/avatars/150/some1@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 3", "https://api.adorable.io/avatars/150/some2@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 4", "https://api.adorable.io/avatars/150/some3@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 5", "https://api.adorable.io/avatars/150/some4@adorable.png"
                    ),
                    RepositoriesProps.RepositoryProps(
                        nop(), "Repo 6", "https://api.adorable.io/avatars/150/some5@adorable.png"
                    )
                )
            )
        )
    }
}