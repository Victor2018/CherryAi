package com.cherry.ai.util

import android.content.Context
import io.noties.markwon.AbstractMarkwonPlugin
import io.noties.markwon.Markwon
import io.noties.markwon.core.MarkwonTheme
import io.noties.markwon.html.HtmlPlugin

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: MarkdownUtil
 * Author: Victor
 * Date: 2023/07/17 16:00
 * Description: 
 * -----------------------------------------------------------------
 */

object MarkdownUtil {
    fun getInstance(
        context: Context
    ): Markwon {
        return Markwon
            .builder(context)
            .usePlugin(HtmlPlugin.create())
            .usePlugin(object : AbstractMarkwonPlugin() {
                override fun configureTheme(builder: MarkwonTheme.Builder) {
                    val bulletWidthPx = context.resources.getDimensionPixelSize(com.victor.screen.match.library.R.dimen.dp_5)
                    builder.bulletWidth(bulletWidthPx)
                }
            })
            .build()
    }
}