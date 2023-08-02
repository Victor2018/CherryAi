package com.cherry.ai.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.text.SpannableString
import android.text.Spanned
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import com.cherry.ai.R


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: FadeInTextView
 * Author: Victor
 * Date: 2023/07/17 18:07
 * Description: 
 * -----------------------------------------------------------------
 */

class FadeInTextView: AppCompatTextView {
    private var start: Long = 0
    private  var letterDuration: Int = 250
    private var isAnimating = false
    private var mInterpolator: Interpolator? = null
    private var charSequence: CharSequence? = null
    private var spannableString: SpannableString? = null
    var textViewListener: TextViewListener? = null

    constructor(context: Context) : this(context,null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context,attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        mInterpolator = DecelerateInterpolator()
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FadeInTextView)
        try {
            // getting letterDuration from XML attribute
            letterDuration = typedArray.getInteger(R.styleable.FadeInTextView_letterDuration, 250)
        } finally {
            typedArray.recycle()
        }
    }


    override fun setText(text: CharSequence, type: BufferType?) {
        charSequence = text
        spannableString = SpannableString(text)
        val letters = spannableString?.getSpans(
            0, spannableString?.length ?: 0,
            Letter::class.java
        )
        letters?.forEach {
            spannableString?.removeSpan(it)
        }
        val length: Int = spannableString?.length ?:0
        if (textViewListener != null) {
            textViewListener?.onTextStart() // firing onTextStart()
        }
        for (i in 0 until length) {
            spannableString?.setSpan(Letter(), i, i + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        super.setText(spannableString, BufferType.SPANNABLE)
        isAnimating = true
        start = AnimationUtils.currentAnimationTimeMillis()
        ViewCompat.postInvalidateOnAnimation(this)
    }

    override fun getText(): CharSequence? {
        return charSequence
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (isAnimating) {
            val mDelta: Long = AnimationUtils.currentAnimationTimeMillis() - start
            val letters = spannableString?.getSpans(
                0, spannableString?.length ?: 0,
                Letter::class.java
            )
            val length = letters?.size ?: 0
            for (i in 0 until length) {
                val letter = letters!![i]
                val delta = Math.max(Math.min(mDelta.toInt() - i * letterDuration, letterDuration), 0).toFloat()
                letter?.alpha = mInterpolator?.getInterpolation(delta / letterDuration) ?: 0f
            }
            if (mDelta < letterDuration * length) {
                ViewCompat.postInvalidateOnAnimation(this)
            } else {
                isAnimating = false
                if (textViewListener != null) {
                    textViewListener?.onTextFinish() // firing onTextFinish()
                }
            }
        }
    }

    // to get the current Animation state
    fun isAnimating(): Boolean {
        return isAnimating
    }

    interface TextViewListener {
        fun onTextStart()
        fun onTextFinish()
    }
}