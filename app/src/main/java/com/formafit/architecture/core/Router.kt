package com.formafit.architecture.core

import android.app.Activity
import androidx.navigation.Navigation
import com.formafit.architecture.R
import com.formafit.architecture.ui.dev.DevFragmentDirections

class Router(activity: Activity) : RoutingOperations {

    override fun popCurrentScreen() {
        navigationController.popBackStack()
    }

    private val navigationController = Navigation.findNavController(activity, R.id.navHostFragment)

    override fun openRepositories() = navigationController.navigate(DevFragmentDirections.openRepositories())

    override fun openPlaybook() = navigationController.navigate(DevFragmentDirections.openPlaybook())
}
