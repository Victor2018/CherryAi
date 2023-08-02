package com.cherry.ai.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherry.ai.data.model.CompletionParm
import com.cherry.ai.data.model.CompletionReq
import com.cherry.ai.data.repository.OpenApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.victor.http.lib.adapter.NetworkResponse
import org.victor.http.lib.data.HttpResult

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: OpenApiViewModel
 * Author: Victor
 * Date: 2023/07/24 16:33
 * Description: 
 * -----------------------------------------------------------------
 */

class OpenApiViewModel(private val openApiRepository: OpenApiRepository): ViewModel() {

    val completionFlowData = MutableStateFlow<HttpResult<CompletionReq>>(HttpResult.Error("0",""))

    fun fetchCompletion(body: CompletionParm?) {
        viewModelScope.launch {
            openApiRepository.fetchCompletion(body = body)
                .catch { e ->
                    completionFlowData.value = HttpResult.Error(null,e.localizedMessage)
                }
                .collect {
                    when (it) {
                        is NetworkResponse.Success -> {
                            completionFlowData.value = HttpResult.Success(it.body)
                        }
                        is NetworkResponse.ApiError -> {
                            val message = it.body.statusMessage
                            completionFlowData.value = HttpResult.Error(null,message)
                        }
                        is NetworkResponse.NetworkError -> {
                            val message = it.error.localizedMessage
                            completionFlowData.value = HttpResult.Error(null,message)
                        }
                        is NetworkResponse.UnknownError -> {
                            val message = it.error?.localizedMessage
                            completionFlowData.value = HttpResult.Error(null,message)
                        }
                        is NetworkResponse.ServerError -> {
                            var code = it.code
                            var error = it.error
                            val message = "code = $code,error = $error"
                            completionFlowData.value = HttpResult.Error(null,message)
                        }
                    }
                }
        }
    }
}