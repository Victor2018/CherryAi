package com.cherry.ai

import androidx.lifecycle.MutableLiveData
import com.cherry.ai.data.api.OpenApiService
import com.cherry.ai.data.model.CompletionParm
import com.cherry.ai.data.model.CompletionReq
import com.cherry.ai.data.model.ImageGenerationParm
import com.cherry.ai.data.model.ImageGenerationReq
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.victor.http.lib.ApiClient
import org.victor.http.lib.data.HttpResult
import org.victor.http.lib.datasource.BaseDataSource

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: OpenApiDataSource
 * Author: Victor
 * Date: 2023/07/17 10:54
 * Description: 
 * -----------------------------------------------------------------
 */

class OpenApiDataSource(private val ioDispatcher: CoroutineDispatcher): BaseDataSource(),
    IOpenApiDataSource {

    override val completionData = MutableLiveData<HttpResult<CompletionReq>>()

    override suspend fun fetchCompletion(body: CompletionParm?) {
        // Force Main thread
        withContext(Dispatchers.Main) {
            completionData.value = completionReq(body)
        }

    }

    override val generateImageData = MutableLiveData<HttpResult<ImageGenerationReq>>()

    override suspend fun generateImage(body: ImageGenerationParm?) {
        // Force Main thread
        withContext(Dispatchers.Main) {
            generateImageData.value = generateImageReq(body)
        }

    }

    private suspend fun <T> completionReq(body: CompletionParm?): T = withContext(ioDispatcher) {

        handleRespone(
            ApiClient.getApiService(OpenApiService::class.java)
            .fetchCompletion(body = body)) as T
    }

    private suspend fun <T> generateImageReq(body: ImageGenerationParm?): T = withContext(ioDispatcher) {

        handleRespone(
            ApiClient.getApiService(OpenApiService::class.java)
            .generateImage(body = body)) as T
    }

}