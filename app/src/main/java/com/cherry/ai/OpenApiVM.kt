package com.cherry.ai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherry.ai.data.model.CompletionParm
import com.cherry.ai.data.model.ImageGenerationParm
import kotlinx.coroutines.launch

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: OpenApiVM
 * Author: Victor
 * Date: 2023/07/17 10:57
 * Description: 
 * -----------------------------------------------------------------
 */

class OpenApiVM(private val dataSource: IOpenApiDataSource): ViewModel() {

    val completionData = dataSource.completionData
    fun fetchCompletion(body: CompletionParm?) {
        // Launch a coroutine that reads from a remote data source and updates cache
        viewModelScope.launch {
            dataSource.fetchCompletion(body)
        }
    }

    val generateImageData = dataSource.generateImageData
    fun generateImage(body: ImageGenerationParm?) {
        // Launch a coroutine that reads from a remote data source and updates cache
        viewModelScope.launch {
            dataSource.generateImage(body)
        }
    }
}