package com.pawegio.blackcat

import org.mockito.Mockito
import rx.android.plugins.RxAndroidPlugins
import rx.android.plugins.RxAndroidSchedulersHook
import rx.plugins.RxJavaHooks
import rx.schedulers.Schedulers

/**
 * @author pawegio
 */

inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)

fun mockRxSchedulers() {
    RxJavaHooks.setOnIOScheduler { Schedulers.immediate() }
    RxAndroidPlugins.getInstance().reset()
    RxAndroidPlugins.getInstance().registerSchedulersHook(object : RxAndroidSchedulersHook() {
        override fun getMainThreadScheduler() = Schedulers.immediate()
    })
}