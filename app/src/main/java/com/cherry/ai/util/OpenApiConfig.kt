package com.cherry.ai.util

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: OpenApiConfig
 * Author: Victor
 * Date: 2023/07/17 11:17
 * Description: 
 * -----------------------------------------------------------------
 */

object OpenApiConfig {
    const val OPEN_AI_API_BASE_URL = "https://api.openai.com/"

    const val AUTHORIZATION = "Authorization"
    const val BEARER_TOKEN_PREFIX = "Bearer "
    const val BEARER_TOKEN = "${BEARER_TOKEN_PREFIX}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"

    const val GPT_MODEL_TYPE = "gpt-3.5-turbo"

    // 0 is the most consistent, 1.0 is the most random and potentially creative
    const val MODEL_TEMPERATURE = 1.0f

    const val USER_ROLE = "user"
    const val ASSISTANT_ROLE = "assistant"
    const val SYSTEM_ROLE = "system"
}