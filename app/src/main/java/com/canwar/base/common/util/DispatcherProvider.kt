package com.canwar.base.common.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherProvider {

    val main: CoroutineDispatcher
        get() = Dispatchers.Main
    val io: CoroutineDispatcher
        get() = Dispatchers.IO
}