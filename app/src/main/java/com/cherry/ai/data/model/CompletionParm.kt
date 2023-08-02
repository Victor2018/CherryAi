package com.cherry.ai.data.model

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: CompletionParm
 * Author: Victor
 * Date: 2023/07/17 10:44
 * Description: 
 * -----------------------------------------------------------------
 */

class CompletionParm {
    var model: String = "gpt-3.5-turbo"
    var messages: List<CompletionRole>? = null
}