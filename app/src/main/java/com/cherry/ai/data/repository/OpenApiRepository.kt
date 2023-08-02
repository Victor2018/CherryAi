package com.cherry.ai.data.repository

import com.cherry.ai.data.api.OpenApiService
import com.cherry.ai.data.model.CompletionParm
import com.cherry.ai.data.model.CompletionReq
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.victor.http.lib.adapter.NetworkResponse
import org.victor.http.lib.data.HttpError

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: OpenApiRepository
 * Author: Victor
 * Date: 2023/07/24 16:28
 * Description: 
 * -----------------------------------------------------------------
 */

class OpenApiRepository(private val openApiService: OpenApiService) {

    fun fetchCompletion(body: CompletionParm?): Flow<NetworkResponse<CompletionReq, HttpError>> {
        return flow {
            emit(openApiService.fetchCompletion(body = body))
        }.map {
            it
        }
    }

}