package com.cherry.ai

import android.app.Application
import org.victor.http.lib.ApiClient

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: App
 * Author: Victor
 * Date: 2023/07/17 10:38
 * Description: 
 * -----------------------------------------------------------------
 */

class App: Application() {
    companion object {
        private var instance : App ?= null
        fun get() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ApiClient.BASE_URL = "https://api.openai.com/"

    }
}