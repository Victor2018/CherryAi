package com.cherry.ai.ui

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: BaseFragment
 * Author: Victor
 * Date: 2022/3/1 18:28
 * Description: 
 * -----------------------------------------------------------------
 */

abstract class BaseFragment : Fragment(),View.OnTouchListener {
    companion object {
        val ID_KEY = "ID_KEY"
        val TAG = javaClass.simpleName
        var fragmentId = -1
    }

    val MULTI_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION)

    protected var rootView: View? = null

    /**
     * 是否初始化过布局
     */
    protected var isViewInitiated = false

    //Fragment对用户可见的标记
    var isVisibleToUser: Boolean = false
    //是否加载过数据
    var isDataInitiated = false

    protected abstract fun getLayoutResource(): Int
    abstract fun handleBackEvent(): Boolean
    abstract fun freshFragData()

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            this.isVisibleToUser = true
            lazyLoad()
        } else {
            this.isVisibleToUser = false
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.e(TAG,"onHiddenChanged()......hidden = ${hidden}")
        if (!hidden) {
            this.isVisibleToUser = true
            lazyLoad()
        } else {
            this.isVisibleToUser = false
        }
    }

    fun lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isVisibleToUser && isViewInitiated && !isDataInitiated) {
            freshFragData()
            //数据加载完毕,恢复标记,防止重复加载
            isDataInitiated = true
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResource(), container, false)
        }

        if (rootView?.parent != null) {
            val parent = rootView?.parent as ViewGroup
            parent?.removeView(rootView)
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewInitiated = true
        view.setOnTouchListener(this)
    }

    /**
     * 已弃用
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }



    override fun onResume() {
        super.onResume()
        isVisibleToUser = true
        if (!isHidden) {
            lazyLoad()
        }
    }

    override fun onPause() {
        super.onPause()
        isVisibleToUser = false
    }


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return true//处理多个fragment叠加点击事件穿透问题
    }


    override fun onDestroyView() {
        super.onDestroyView()
        isViewInitiated = false
        isVisibleToUser = false
        isDataInitiated = false
    }


}