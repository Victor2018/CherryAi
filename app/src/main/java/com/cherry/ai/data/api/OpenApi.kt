package com.cherry.ai.data.api

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: OpenApi
 * Author: Victor
 * Date: 2023/07/17 10:39
 * Description: 
 * -----------------------------------------------------------------
 */

object OpenApi {
    const val COMPLETION = "v1/chat/completions"
    const val IMG_GENERATION = "v1/images/generations"
}