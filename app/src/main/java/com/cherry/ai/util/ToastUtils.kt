package com.cherry.ai.util

import android.widget.Toast
import androidx.annotation.IntegerRes
import com.cherry.ai.App
import com.cherry.ai.BuildConfig

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: ToastUtils.java
 * Author: Victor
 * Date: 2022/3/1 18:28
 * Description: 吐司工具类
 * -----------------------------------------------------------------
 */

object ToastUtils {

    /**
     * 调试模式下可显示
     *
     * @param msg
     */
    fun showDebug(msg: String) {
        if (BuildConfig.DEBUG) {
            Toast.makeText(App.get(), msg, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 调试模式下可显示
     *
     * @param resId
     */
    fun showDebug(@IntegerRes resId: Int) {
        if (BuildConfig.DEBUG) {
            val text = ResUtils.getStringRes(resId)
            Toast.makeText(App.get(), text, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 短暂显示
     *
     * @param msg
     */
    fun showShort(msg: CharSequence) {
        Toast.makeText(App.get(), msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * 短暂显示
     *
     * @param resId
     */
    fun showShort(resId: Int) {
        val text = ResUtils.getStringRes(resId)
        Toast.makeText(App.get(), text, Toast.LENGTH_SHORT).show()
    }

    /**
     * 长时间显示
     *
     * @param msg
     */
    fun showLong(msg: CharSequence) {
        Toast.makeText(App.get(), msg, Toast.LENGTH_LONG).show()
    }

    /**
     * 短暂显示
     *
     * @param resId
     */
    fun showLong(resId: Int) {
        val text = ResUtils.getStringRes(resId)
        Toast.makeText(App.get(), text, Toast.LENGTH_LONG).show()
    }

}