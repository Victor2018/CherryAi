package com.cherry.ai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.Dispatchers
import org.victor.http.lib.vm.BaseVMFactory
import kotlin.reflect.KClass

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: OpenApiVMFactory
 * Author: Victor
 * Date: 2023/07/17 10:58
 * Description: 
 * -----------------------------------------------------------------
 */

class OpenApiVMFactory(owner: SavedStateRegistryOwner) : BaseVMFactory(owner) {

    override fun getVM(): ViewModel {
        return OpenApiVM(OpenApiDataSource(Dispatchers.IO))
    }

}