package com.cherry.ai.ui.widget

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.text.TextUtils
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import androidx.appcompat.widget.AppCompatTextView
import com.cherry.ai.util.MarkdownUtil
import io.noties.markwon.Markwon
import kotlinx.android.synthetic.main.rv_message_text_recv_cell.view.*


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: FadeTextView
 * Author: Victor
 * Date: 2023/07/17 18:07
 * Description: 
 * -----------------------------------------------------------------
 */

class FadeTextView: AppCompatTextView {

    private val textRect = Rect()

    private val stringBuffer = StringBuffer()

    private var arr = ArrayList<String>()

    private var textCount = 0
    private var content: String = ""

    private var currentIndex = -1

    private var textAnimation: ValueAnimator? = null

    var mTextAnimationListener: TextAnimationListener? = null

    constructor(context: Context) : this(context,null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        使用setText代替重绘就不用自己去绘制text了
//        if (stringBuffer != null) {
//            drawText(canvas, stringBuffer.toString());
//        }
    }

    /**
     * 绘制文字
     *
     * @param canvas 画布
     */
    private fun drawText(canvas: Canvas, textString: String) {
        textRect.left = paddingLeft
        textRect.top = paddingTop
        textRect.right = width - paddingRight
        textRect.bottom = height - paddingBottom
        val fontMetrics = paint.fontMetricsInt
        val baseline = (textRect.bottom + textRect.top - fontMetrics.bottom - fontMetrics.top) / 2
        //文字绘制到整个布局的中心位置
        canvas.drawText(textString, paddingLeft.toFloat(), baseline.toFloat(), paint)
    }

    /**
     * 文字逐个显示动画  通过插值的方式改变数据源
     */
    private fun initAnimation() {

        //从0到textCount - 1  是设置从第一个字到最后一个字的变化因子
        textAnimation = ValueAnimator.ofInt(0, textCount - 1)
        //执行总时间就是每个字的时间乘以字数
        textAnimation?.duration = textCount * getLetterAnimDuration()
        //匀速显示文字
        textAnimation?.interpolator = LinearInterpolator()
        textAnimation?.addUpdateListener(AnimatorUpdateListener { valueAnimator ->
            val index = valueAnimator.animatedValue as Int
            //过滤去重，保证每个字只重绘一次
            if (currentIndex != index) {
                stringBuffer.append(arr[index])
                currentIndex = index
                //所有文字都显示完成之后进度回调结束动画
                if (currentIndex == textCount - 1) {
                    mTextAnimationListener?.animationFinish()
                    MarkdownUtil.getInstance(context).setMarkdown(this,"$content")
                } else {
                    //新思路的做法
//                    text = stringBuffer.toString()
                    MarkdownUtil.getInstance(context).setMarkdown(this, "$stringBuffer ▏")
                    /**
                     * 之前的做法刷新重绘text,需要自己控制文字的绘制，
                     * 看到网友的评论开拓了思路，既然是直接集成TextView
                     * 就可以直接使用setText()方法进行设置值了
                     */
                    //invalidate();老思路的做法
                }

            }
        })
    }

    /**
     * 设置逐渐显示的字符串
     *
     * @param textString
     * @return
     */
    fun setTextString(textString: String?): FadeTextView? {
        content = textString ?: ""
        if (!TextUtils.isEmpty(content)) {
            //总字数
            textCount = content.length
            //存放单个字的数组
            arr.clear()
            for (i in 0 until textCount) {
                arr.add(content.substring(i, i + 1))
            }
            initAnimation()
        }
        return this
    }

    fun typerAnimationFinish() : Boolean {
        return textCount > 0 && currentIndex == textCount - 1
    }
    /**
     * 开启动画
     *
     * @return
     */
    fun startFadeInAnimation(): FadeTextView? {
        if (textAnimation != null && !typerAnimationFinish()) {
            stringBuffer.setLength(0)
            currentIndex = -1
            textAnimation?.start()
        }
        return this
    }

    /**
     * 停止动画
     *
     * @return
     */
    fun stopFadeInAnimation(): FadeTextView? {
        if (textAnimation != null) {
            textAnimation?.end()
        }
        return this
    }

    /**
     * 每个字显示的时间
     */
    fun getLetterAnimDuration(): Long {
        var duration = 110L
        when (textCount) {
            in 0..1000 -> {
                duration -= textCount / 10
            }
            else -> {
                duration = 5
            }
        }
        return duration
    }

    /**
     * 回调接口
     */
    interface TextAnimationListener {
        fun animationFinish()
    }
}