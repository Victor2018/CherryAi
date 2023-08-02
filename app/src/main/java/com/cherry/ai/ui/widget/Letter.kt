package com.cherry.ai.ui.widget

import android.text.TextPaint
import android.text.style.CharacterStyle
import android.text.style.UpdateAppearance

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: Letter
 * Author: Victor
 * Date: 2023/07/17 18:19
 * Description: 
 * -----------------------------------------------------------------
 */

class Letter: CharacterStyle(), UpdateAppearance {
    var alpha = 0.0f
    override fun updateDrawState(tp: TextPaint?) {
        try {
            val color = (0xFF * alpha).toInt() shl 24 or (tp?.color ?: 0 and 0x00FFFFFF)
            tp?.color = color
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}