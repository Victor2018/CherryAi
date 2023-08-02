package com.hok.lib.common.base

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: BaseActivity
 * Author: Victor
 * Date: 2022/3/1 18:28
 * Description: 
 * -----------------------------------------------------------------
 */

abstract class BaseActivity: AppCompatActivity() {
    /**
     * 是否需要使用DataBinding 供子类BaseVmDbActivity修改，用户请慎动
     */
    private var isUserDb = false
    protected var TAG = javaClass.simpleName
    var statusBarTextColorBlack: Boolean = true
    var setNavigationBarBottomPading: Boolean = true
    private var neededPermission: Array<String>? = null
    private var builder: AlertDialog? = null

    val MULTI_PERMISSIONS = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)

    abstract fun getLayoutResource(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isUserDb) {
            setContentView(getLayoutResource())
        } else {
            initDataBind()
        }
        initializeSuper()
    }

    /**
     * 供子类BaseDbActivity 初始化DataBinding操作
     */
    open fun initDataBind() {}

    internal fun userDataBinding(isUserDb: Boolean) {
        this.isUserDb = isUserDb
    }

    fun initializeSuper () {
        Log.e(TAG,"initializeSuper......")
        //禁止app录屏和截屏
//        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }

}