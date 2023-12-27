package com.cherry.ai.ui.widget

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: TypeWriteTextView
 * Author: Victor
 * Date: 2023/12/27 11:11
 * Description: 
 * -----------------------------------------------------------------
 */

class TypeWriteTextView: AppCompatTextView {
    /**
     * 打字间隔
     */
    private val TYPE_TIME_INTERVAL = 66
    private val KEY_TEXT = "KEY_TEXT"
    private val DEFAULT_DATA = "DEFAULT_DATA"

    private var mTypeListener: OnTypeListener? = null
    private var mAnimatorTypeText: ValueAnimator? = null
    private var mTextLength = 0
    private var mText: String? = null

    constructor(context: Context) : this(context,null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        release()
        mAnimatorTypeText = ValueAnimator()
        mAnimatorTypeText?.addUpdateListener(AnimatorUpdateListener { animation ->
            val value = animation.animatedValue as Int
            //value的值会重复所以要加判断，避免文字重复打印
            if (text.length - 1 < value) {
                if (value == 0) {
                    if (mTypeListener != null) {
                        mTypeListener?.onTypeStart()
                    }
                }
                append(mText?.substring(value, value + 1))
            }
            if (value >= mTextLength - 1) {
                if (mTypeListener != null) {
                    mTypeListener?.onTypeStop()
                }
                release()
            }
        })
    }

    fun setTypeListener(typeListener: OnTypeListener) {
        mTypeListener = typeListener
    }

    fun bindText(text: String) {
        setText("")
        initView()
        mTextLength = text.length
        mText = text
        mAnimatorTypeText?.setIntValues(0, mTextLength - 1)
        mAnimatorTypeText?.duration = (mTextLength * TYPE_TIME_INTERVAL).toLong()
        mAnimatorTypeText?.start()
    }



    /**
     * 释放资源
     */
    fun release() {
        if (mAnimatorTypeText != null && mAnimatorTypeText?.isRunning == true) {
            mAnimatorTypeText?.cancel()
            mAnimatorTypeText = null
        }
    }

    /**
     * 切换程序或者按下home键直接setText()
     */

    override fun onSaveInstanceState(): Parcelable? {
        release()
        val bundle = Bundle()
        val superData = super.onSaveInstanceState()
        bundle.putParcelable(DEFAULT_DATA, superData)
        bundle.putString(KEY_TEXT, mText)
        text = mText
        return bundle
    }

    /**
     * 后台杀死回复
     * @param state
     */
    override fun onRestoreInstanceState(state: Parcelable) {
        val bundle = state as Bundle
        mText = bundle.getString(KEY_TEXT)
        text = mText
        val superData = bundle.getParcelable<Parcelable>(DEFAULT_DATA)
        super.onRestoreInstanceState(superData)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        release()
    }

    interface OnTypeListener {
        fun onTypeStart()
        fun onTypeStop()
    }
}