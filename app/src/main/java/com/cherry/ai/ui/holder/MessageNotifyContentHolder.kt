package com.cherry.ai.ui.holder

import android.view.View
import com.cherry.ai.data.model.ChatMessage
import com.cherry.ai.util.TimeUtils
import kotlinx.android.synthetic.main.rv_message_notify_cell.view.*

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2020-2080, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: MessageNotifyContentHolder
 * Author: Victor
 * Date: 2020/12/30 10:34
 * Description: 
 * -----------------------------------------------------------------
 */
class MessageNotifyContentHolder(itemView: View) : ContentViewHolder(itemView) {

    fun bindData (data: ChatMessage?) {
        itemView.mTvRetry.setOnClickListener(this)

        TimeUtils.bindShowTime(itemView.mTvTime, data?.time)
        itemView.mTvNotify.text = data?.content
    }

    override fun onLongClick(v: View): Boolean {
        return false
    }

}