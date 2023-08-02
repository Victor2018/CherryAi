package com.cherry.ai.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: ViewModelProviderFactory
 * Author: Victor
 * Date: 2023/07/24 16:48
 * Description: 
 * -----------------------------------------------------------------
 */

class ViewModelProviderFactory<T : ViewModel>(
    private val kClass: KClass<T>,
    private val creator: () -> T
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(kClass.java)) return creator() as T
        throw IllegalArgumentException("Unknown class name")
    }

}