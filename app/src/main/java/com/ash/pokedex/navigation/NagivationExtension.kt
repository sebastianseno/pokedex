package com.ash.pokedex.navigation

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

fun NavBackStackEntry.lifecycleIsResumed() = this.lifecycle.currentState == Lifecycle.State.RESUMED

fun NavController.navigateWithPopUp(
    toRoute: String,
    fromRoute: String,
    navBackStackEntry: NavBackStackEntry,
) {
    this.navigate(toRoute) {
        if (navBackStackEntry.lifecycleIsResumed()) {
            popUpTo(fromRoute) {
                inclusive = true
            }
        }
    }
}

fun NavController.back() {
    if (this.currentBackStackEntry!!.lifecycleIsResumed()) {
        this.popBackStack()
    }
}

fun NavController.navigateTo(toRoute: String) {
    if (this.currentBackStackEntry!!.lifecycleIsResumed()) {
        navigate(toRoute)
    }
}

fun NavController.navigateTo(
    toRoute: String,
    navBackStackEntry: NavBackStackEntry,
) {
    if (navBackStackEntry.lifecycleIsResumed()) {
        navigate(toRoute)
    }
}

fun <S> NavController.sendBundle(
    key: String,
    value: S,
) {
    this.currentBackStackEntry?.savedStateHandle?.set(key, value)
}

fun <S> NavController.receiveBundle(key: String): S? {
    return previousBackStackEntry?.savedStateHandle?.get(key)
}
