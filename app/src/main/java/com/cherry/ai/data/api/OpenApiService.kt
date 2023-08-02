package com.cherry.ai.data.api

import com.cherry.ai.data.model.CompletionParm
import com.cherry.ai.data.model.CompletionReq
import com.cherry.ai.data.model.ImageGenerationParm
import com.cherry.ai.data.model.ImageGenerationReq
import com.cherry.ai.util.OpenApiConfig
import org.victor.http.lib.adapter.NetworkResponse
import org.victor.http.lib.data.HttpError
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: OpenApiService
 * Author: Victor
 * Date: 2023/07/17 10:42
 * Description: 
 * -----------------------------------------------------------------
 */

interface OpenApiService {

    @POST(OpenApi.COMPLETION)
    suspend fun fetchCompletion(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") authToken: String = OpenApiConfig.BEARER_TOKEN,
        @Body body: CompletionParm?):
            NetworkResponse<CompletionReq, HttpError>

    @POST(OpenApi.IMG_GENERATION)
    suspend fun generateImage(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") authToken: String = OpenApiConfig.BEARER_TOKEN,
        @Body body: ImageGenerationParm?):
            NetworkResponse<ImageGenerationReq, HttpError>
}