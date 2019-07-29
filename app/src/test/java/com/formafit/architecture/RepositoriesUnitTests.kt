package com.formafit.architecture

import com.formafit.architecture.core.NetworkOperations
import com.formafit.architecture.network.models.RepositoriesResponseModel
import com.formafit.architecture.ui.repositories.RepositoriesProps
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.ArgumentMatchers.any

class RepositoriesUnitTests : BaseCoreTests() {

    val repositoriesResponseModels = listOf(
        RepositoriesResponseModel(
            1,
            false,
            "Repo 1",
            RepositoriesResponseModel.Owner(1, "Developer 1", "http://avatar.com")
        ),
        RepositoriesResponseModel(
            2,
            false,
            "Repo 2",
            RepositoriesResponseModel.Owner(2, "Developer 2", "http://avatar.com")
        ),
        RepositoriesResponseModel(
            3,
            false,
            "Repo 3",
            RepositoriesResponseModel.Owner(3, "Developer 3", "http://avatar.com")
        ),
        RepositoriesResponseModel(
            4,
            false,
            "Repo 4",
            RepositoriesResponseModel.Owner(4, "Developer 4", "http://avatar.com")
        )
    )

    @Test
    fun `repositories result is server error`() {
        every { routingOperations.openRepositories() } just runs
        every { activityOperations.showToast(any()) } just runs
        coEvery { networkOperations.getAllRepositories() } coAnswers {
            verifyUIisLoading()
            NetworkOperations.Result.ServerUnavailable()
        }

        runBlocking {
            core.repositoriesPresenter.openRepositories()
        }

        verify { routingOperations.openRepositories() }
        verify { activityOperations.showToast("Sorry, server is unavailable at the moment") }
        assertNotNull(core.repositoriesProps.value)
        assertEquals(RepositoriesProps.Repositories.Empty, core.repositoriesProps.value?.repositories)
    }

    @Test
    fun `repositories result is empty`() {
        coEvery { networkOperations.getAllRepositories() } coAnswers {
            verifyUIisLoading()
            NetworkOperations.Result.Data(listOf())
        }
        every { routingOperations.openRepositories() } just runs

        runBlocking {
            core.repositoriesPresenter.openRepositories()
        }

        verify { routingOperations.openRepositories() }
        assertNotNull(core.repositoriesProps.value)
        assertEquals(RepositoriesProps.Repositories.Empty, core.repositoriesProps.value?.repositories)
    }

    @Test
    fun `repositories result is data`() {
        coEvery { networkOperations.getAllRepositories() } coAnswers {
            verifyUIisLoading()
            NetworkOperations.Result.Data(repositoriesResponseModels)
        }
        every { routingOperations.openRepositories() } just runs

        runBlocking {
            core.repositoriesPresenter.openRepositories()
        }

        verify { routingOperations.openRepositories() }
        assertNotNull(core.repositoriesProps.value)
        assertEquals(RepositoriesProps.Repositories.Loaded(any()), core.repositoriesProps.value?.repositories)
        assertEquals(4, (core.repositoriesProps.value?.repositories as RepositoriesProps.Repositories.Loaded).items.size)
    }

    private fun verifyUIisLoading() {
        assertNull(core.repositoriesPresenter.repositoriesState.list)
        assertEquals(RepositoriesProps.Repositories.Loading, core.repositoriesProps.value?.repositories)
    }

}
