package com.cherry.ai.data.model

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: CompletionReq
 * Author: Victor
 * Date: 2023/07/17 10:46
 * Description: 
 * -----------------------------------------------------------------
 */

class CompletionReq {
    var id: String? = null
    var  `object`: String? = null
    var created: Long = 0
    var model: String? = null
    var choices: List<ChoiceInfo>? = null
    var usage: UsageInfo? = null
}