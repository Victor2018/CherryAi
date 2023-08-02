package com.cherry.ai.util

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner
import com.cherry.ai.OpenApiVMFactory
import com.cherry.ai.data.api.OpenApiService
import com.cherry.ai.data.repository.OpenApiRepository
import com.cherry.ai.ui.vm.OpenApiViewModel
import com.cherry.ai.ui.vm.ViewModelProviderFactory
import org.victor.http.lib.ApiClient

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: InjectorUtils
 * Author: Victor
 * Date: 2023/07/17 10:59
 * Description: 
 * -----------------------------------------------------------------
 */

object InjectorUtils {
    fun provideOpenApiVM(owner: SavedStateRegistryOwner): OpenApiVMFactory {
        return OpenApiVMFactory(owner)
    }

    fun provideOpenApiViewModel(
        owner: ViewModelStoreOwner): OpenApiViewModel {

        return ViewModelProvider(owner,
            ViewModelProviderFactory(OpenApiViewModel::class) {
                OpenApiViewModel(OpenApiRepository(ApiClient.getApiService(OpenApiService::class.java)))
            })[OpenApiViewModel::class.java]
    }

}