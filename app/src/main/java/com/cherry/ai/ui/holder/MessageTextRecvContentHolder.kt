package com.cherry.ai.ui.holder

import android.view.View
import com.cherry.ai.data.model.ChatMessage
import com.cherry.ai.util.TimeUtils
import kotlinx.android.synthetic.main.rv_message_text_recv_cell.view.*


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2020-2080, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: MessageTextRecvContentHolder
 * Author: Victor
 * Date: 2020/12/30 10:34
 * Description: 
 * -----------------------------------------------------------------
 */
class MessageTextRecvContentHolder(itemView: View) : ContentViewHolder(itemView) {

    fun bindData (data: ChatMessage?) {
        itemView.mTvMessage.setOnClickListener(this)

        TimeUtils.bindShowTime(itemView.mTvTime, data?.time)
        itemView.mTvMessage.setTextString(data?.content ?: "")
    }

    fun startFadeInAnimation() {
        itemView.mTvMessage.startFadeInAnimation()
    }

    fun stopFadeInAnimation() {
        itemView.mTvMessage.stopFadeInAnimation()
    }

    override fun onLongClick(v: View): Boolean {
        return false
    }
}