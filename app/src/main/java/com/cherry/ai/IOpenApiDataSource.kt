package com.cherry.ai

import androidx.lifecycle.LiveData
import com.cherry.ai.data.model.CompletionParm
import com.cherry.ai.data.model.CompletionReq
import com.cherry.ai.data.model.ImageGenerationParm
import com.cherry.ai.data.model.ImageGenerationReq
import org.victor.http.lib.data.HttpResult

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: IOpenApiDataSource
 * Author: Victor
 * Date: 2023/07/17 10:53
 * Description: 
 * -----------------------------------------------------------------
 */

interface IOpenApiDataSource {
    val completionData: LiveData<HttpResult<CompletionReq>>
    suspend fun fetchCompletion(body: CompletionParm?)

    val generateImageData: LiveData<HttpResult<ImageGenerationReq>>
    suspend fun generateImage(body: ImageGenerationParm?)
}