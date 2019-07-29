package com.formafit.architecture

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.formafit.architecture.core.ActivityOperations
import com.formafit.architecture.core.Core
import com.formafit.architecture.core.NetworkOperations
import com.formafit.architecture.core.RoutingOperations
import io.mockk.clearMocks
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule

open class BaseCoreTests {

    lateinit var core: Core

    var networkOperations = mockk<NetworkOperations>()

    var activityOperations = mockk<ActivityOperations>()

    var routingOperations = mockk<RoutingOperations>()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        core = Core(networkOperations)

        Command.coroutine = { action ->
            runBlocking {
                action()
            }
        }

        core.registerActivity(activityOperations)
        core.registerRouting(routingOperations)
    }

    @After
    fun after() {
        clearMocks(networkOperations, activityOperations)
    }
}