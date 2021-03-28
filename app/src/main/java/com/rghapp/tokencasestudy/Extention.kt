package com.rghapp.tokencasestudy

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * Created by Rasool Ghana on 3/28/21.
 * Email : Rasool.ghana@gmail.com
 */

fun Activity.isNetworkAvailable(): Boolean {
    val conMgr =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    @Suppress("DEPRECATION") val netInfo = conMgr!!.activeNetworkInfo
    return netInfo != null
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>?, action: (t: T) -> Unit) {
    liveData?.observe(this, { t -> action(t) })
}
