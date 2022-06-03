package com.example.baseproject.framework.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.example.baseproject.business.entities.Profile
import com.example.baseproject.framework.utils.Constants.Search.DEBOUNCE_TIME
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.random.Random

fun List<Profile>.getOneRandom(): Profile {
    val randomIndex = Random.nextInt(this.size)
    return this[randomIndex]
}

/**
 * debounce: Returns a flow that mirrors the original flow, but filters out values that are followed
 * by the newer values within the given timeout. The latest value is always emitted.
 * distinctUntilChanged: Returns flow where all subsequent repetitions of the same value are filtered out.
 **/
@FlowPreview
fun <T> MutableLiveData<T>.debounceFlow(scope: CoroutineScope, actionToTake: (T) -> Unit) {
    this.asFlow().debounce { DEBOUNCE_TIME }
        .distinctUntilChanged()
        .onEach { query -> actionToTake(query) }
        .launchIn(scope)
}

@SuppressLint("QueryPermissionsNeeded")
fun Context.isIntentCanBeHandled(intent: Intent): Boolean {
    // Get all apps that can handle this intent.
    return this.packageManager.queryIntentActivities(intent, 0).isNotEmpty()
}

fun Activity.showSnackBar(msg: String) {
    Snackbar.make(this.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show()
}